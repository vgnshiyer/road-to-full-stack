<template>
  <section>
    <h1>{{ categoryTitle }}</h1>
    <div class="question" v-for="question in questions" :key="question.question">
      <Box :question="question" :questionSelected="questionSelected" :answerUpdate="answerUpdate" @question-selected="handleQuestionSelect"/>
    </div>
  </section>
</template>

<script>
import Box from './Box.vue';

export default {
  name: 'Board',
  props: ['category', 'questionSelected', 'answerUpdate'],
  components: {
    Box
  },
  data() {
    return {
      categoryData: [],
      categoryTitle: ''
    };
  },
  computed: {
    questions() {
      return this.categoryData?.map((item) => {
        return {
          question: item.question,
          category: item.category,
          answer: item.correct_answer,
          value: item.value,
          answered: false,
          answeredCorrectly: null,
          player: null,
        };
      });
    }
  },
  async created() {
    if (this.categoryData.length === 0) {
      await this.getData();
    }
  },
  methods: {
    async getData() {
      const type = 'boolean';
      const base_url = 'https://opentdb.com/api.php?';

      try {
        const categoryResponse = await fetch('https://opentdb.com/api_category.php');
        const categoryData = await categoryResponse.json();
        const category = categoryData.trivia_categories.find(item => item.id === this.category);
        this.categoryTitle = category.name.replace("Entertainment: ", "").replace("Science: ", "")

        if (this.categoryData.length === 0) {
          let response = null;
          let data = null;

          // 2 easy questions
          response = await fetch(base_url + 'amount=2&category=' + this.category + '&difficulty=easy&type=' + type);
          data = await response.json();
          data.results[0].value = 100;
          data.results[1].value = 200;
          this.categoryData.push(...data.results);

          // 2 medium questions
          response = await fetch(base_url + 'amount=2&category=' + this.category + '&difficulty=medium&type=' + type);
          data = await response.json();
          data.results[0].value = 300;
          data.results[1].value = 400;
          this.categoryData.push(...data.results);

          // 1 hard question
          response = await fetch(base_url + 'amount=1&category=' + this.category + '&difficulty=hard&type=' + type);
          data = await response.json();
          data.results[0].value = 500;
          this.categoryData.push(...data.results);
        }
      } catch (error) {
        console.log(error);
      }
    },
    handleQuestionSelect(value) {
      this.$emit('question-selected', value);
    }
  }
};
</script>


<style scoped>
section {
  margin-top: 50px;
  display: grid;
  grid-row-gap: 3px;
  grid-template-rows: 70px 70px 70px 70px 70px 70px;
}

h1 {
  background: #16228d;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size: large;
  text-transform: uppercase;
  margin: 0;
  margin-bottom: 10px;
  padding: 1rem;
  text-shadow: 2px 2px 1px rgb(3, 3, 3);
  text-overflow: ellipsis;
  overflow: hidden;
}

.question {
  background: #16228d;
}
</style>