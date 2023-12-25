<template>
  <div class="main-container" v-if="!gameStarted">
    <div>
      <label for="numPlayers">Number of players:</label>
      <select id="numPlayers" v-model="numPlayers" @change="updatePlayers(numPlayers)">
        <option v-for="i in maxPlayers - 1" :key="i + 1" :value="i + 1">{{ i + 1 }}</option>
      </select>
    </div>

    <div>
      <label for="numCategories">Number of categories:</label>
      <select id="numCategories" v-model="numCategories" @change="updateCategories(numCategories)">
        <option v-for="i in maxColumns" :key="i" :value="i">{{ i }}</option>
      </select>
    </div>

    <button @click="startGame">Start Game</button>
  </div>

  <div id="app" v-if="gameStarted">
    <Board v-for="index in numCategories" :key="index" :category="categories[index - 1]"
      :questionSelected="questionSelected" :answerUpdate="answerUpdate" @question-selected="handleQuestionSelect" />
  </div>
  <div class="main-container" v-if="gameStarted">
    <h1 style="color:#d69e4c">Jeopardy!</h1>
    <p>It's <span style="color: greenyellow">{{ players[currentPlayer].name }}</span>'s turn!</p>

    <p style="color: #d69e4c">Scoreboard:</p>
    <div v-for="player in players" :key="player.id" class="player-score">
      <p>{{ player.name }}: {{ player.score < 0 ? '-' : '' }}${{ Math.abs(player.score) }}</p>
    </div>

    <br>
    <span v-if="winner">
      <div>
        <h2 style="color:#d69e4c">We have a winner!</h2>
        <p v-if="winner"><span style="color: greenyellow">{{ winner.name }}</span> has won the game!</p>
      </div>
    </span>
  </div>
  <div class="question-container" v-if="gameStarted">
    <span v-if="currentQuestion != null">
      <br>
      <p>Category: <span style="color: #d69e4c; font-weight: 900;">{{ currentQuestion.category
      }}</span></p>
      <p>Value: <span style="color: #d69e4c; font-weight: 900;">${{ currentQuestion.value
      }}</span>
      </p>
      <p>Question: <span style="color: #d69e4c; font-weight: 900;">{{ currentQuestion.question
      }}</span></p>
      <span v-if="currentQuestion.answered">
        <p v-if="currentQuestion.answeredCorrectly" style="color: greenyellow">Correct!</p>
        <p v-if="!currentQuestion.answeredCorrectly" style="color: red;">Incorrect!</p>
        <button @click="handleClose">Close</button>
      </span>

      <span v-if="!currentQuestion.answered">
        <button @click="checkAnswer('True')">True</button>
        <button @click="checkAnswer('False')">False</button>
      </span>
    </span>
  </div>
</template>

<script>
import Board from './components/Board.vue'
import { EXCLUDED_CATEGORIES, MAX_CATEGORIES, DOUBLE_JEOPARDY_PROBABILITY, NUM_QUESTIONS, DEFAULT_PLAYERS, DEFAULT_COLUMNS } from '../config.js';

export default {
  name: 'App',
  components: {
    Board
  },
  data() {
    return {
      gameStarted: false,
      maxPlayers: 6,
      maxColumns: 6,
      categories: [],
      players: [],
      currentPlayer: 0,
      winner: null,
      numQuestionsRevealed: 0,
      currentQuestion: null,
      questionSelected: false,
      answerUpdate: false,
      numPlayers: DEFAULT_PLAYERS,
      numCategories: DEFAULT_COLUMNS
    }
  },
  methods: {
    startGame() {
      this.players = Array.from({ length: this.numPlayers }, (_, i) => ({ name: `Player ${i + 1}`, score: 0 }));
      this.selectRandomCategories(this.numCategories);
      this.gameStarted = true;
    },
    selectRandomCategories(numCategories) {
      const excluded = EXCLUDED_CATEGORIES;
      const maxCategories = MAX_CATEGORIES;
      const availableCategories = [...Array(maxCategories).keys()]
        .map(i => i + 9)
        .filter(i => !excluded.includes(i));

      if (availableCategories.length < numCategories) {
        throw new Error('Not enough categories available');
      }

      this.categories = availableCategories
        .map(value => ({ value, sort: Math.random() }))
        .sort((a, b) => a.sort - b.sort)
        .map(({ value }) => value)
        .slice(0, numCategories);
    },
    getPlayerName(player) {
      return `P${player + 1}`;
    },
    handleCorrectAnswer(value) {
      this.players[this.currentPlayer].score += value;
      this.currentQuestion.answeredCorrectly = true;
      this.currentQuestion.player = this.getPlayerName(this.currentPlayer);
      this.answerUpdate = !this.answerUpdate;
    },
    handleIncorrectAnswer(value) {
      this.players[this.currentPlayer].score -= value;
      this.currentQuestion.answeredCorrectly = false;
      this.currentQuestion.player = this.getPlayerName(this.currentPlayer);
      this.currentPlayer = (this.currentPlayer + 1) % this.players.length;
      this.answerUpdate = !this.answerUpdate;
    },
    handleBoardComplete() {
      this.winner = this.players.reduce((winner, player) => {
        if (winner.score > player.score) {
          return winner;
        }

        return player;
      }, this.players[0]);
    },
    handleQuestionSelect(question) {
      this.currentQuestion = question;
      this.questionSelected = true;
      this.numQuestionsRevealed += 1;

      // check if Double Jeopardy should be triggered
      const doubleJeopardyProbability = DOUBLE_JEOPARDY_PROBABILITY;
      if (Math.random() < doubleJeopardyProbability) {
        const maxWager = Math.max(this.players[this.currentPlayer].score, question.value);
        if (this.players[this.currentPlayer].score < question.value) {
          // cannot afford to wager an amount greater than current value
          this.currentQuestion.value = question.value;
        } else {
          let wager = prompt(`Double Jeopardy wager: ___(up to $${maxWager}):`);
          while (wager !== null) {
            const parsedWager = parseInt(wager);
            if (!isNaN(parsedWager) && parsedWager >= 0 && parsedWager <= maxWager) {
              this.currentQuestion.value = parsedWager;
              break;
            }
            wager = prompt(`Invalid wager amount. Please enter a number between 0 and ${maxWager}:`);
          }
        }
      }
    },
    handleClose() {
      this.currentQuestion = null;
      this.questionSelected = false;
      this.checkBoardCompleted();
    },
    checkBoardCompleted() {
      if (this.numQuestionsRevealed === this.numCategories * NUM_QUESTIONS) {
        this.handleBoardComplete();
      }
    },
    checkAnswer(userAnswer) {
      if (this.currentQuestion.answered) return;
      this.currentQuestion.answered = true;
      if (userAnswer.toLowerCase() === this.currentQuestion.answer.toLowerCase()) {
        this.handleCorrectAnswer(this.currentQuestion.value);
      } else {
        this.handleIncorrectAnswer(this.currentQuestion.value);
      }
    },
    updatePlayers(value) {
      this.numPlayers = value;
    },
    updateCategories(value) {
      this.numCategories = value;
    }
  }
}
</script>

<style scoped>
html,
body,
#app {
  min-height: 100vh;
}

#app {
  margin-left: -50px;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
  display: grid;
  grid-template-columns: 150px 150px 150px 150px 150px 150px;
  grid-column-gap: 3px;
}

.main-container {
  margin-top: 50px;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size: 2rem;
  text-transform: uppercase;
  letter-spacing: .05em;
  text-shadow: 3px 3px 0px rgb(3, 3, 3);
  color: white;
}

.question-container {
  margin-top: -100px;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size: 1.5rem;
  letter-spacing: .05em;
  text-transform: capitalize;
  text-shadow: 3px 3px 0px rgb(3, 3, 3);
  color: white;
}

button {
  border: solid 3px white;
  color: white;
  border-radius: 6px;
  background-color: #004aaa;
  padding: 10px 20px;
  margin: 5px;
  font-weight: bolder;
  font-size: 1rem;
  cursor: pointer;
}

select {
  border: solid 3px white;
  color: white;
  border-radius: 6px;
  background-color: #004aaa;
  padding: 2px 20px;
  height: 30px;
  margin: 10px;
  font-weight: bolder;
  font-size: 1rem;
  cursor: pointer;
}
</style>
