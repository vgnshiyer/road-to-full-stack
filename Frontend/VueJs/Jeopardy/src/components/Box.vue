<template>
    <div class="container">
        <div class="cell" v-if="!questionRevealed" @click="selectQuestion(question)">
            <p>${{ question.value }}</p>
        </div>
        <div class="cell" v-if="questionRevealed">
            <p v-if="question.answeredCorrectly" style="color:greenyellow">{{ question.player }}</p>
            <p v-if="!question.answeredCorrectly" style="color:red">{{ question.player }}</p>
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'Box',
    props: ['question', 'questionSelected', 'answerUpdate'],
    data() {
        return {
            questionRevealed: false,
        };
    },
    components: {
    },
    methods: {
        selectQuestion(question) {
            if (this.questionSelected) return;
            this.$emit('question-selected', question);
            this.questionRevealed = true;
        }
    },
    watch: {
        answerUpdate() {
            this.$forceUpdate();
        }
    }
};
</script>
  
<style scoped>  
  .container {
      display: flex;
      justify-content: center;
      align-items: center;
      font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
      height: 100%;
  }

  .cell {
      font-weight: 400;
      color: #d69e4c;
      font-size: 2.5rem;
      text-shadow: 3px 3px 0px rgb(3, 3, 3);
      cursor: pointer;
  }
</style>
  