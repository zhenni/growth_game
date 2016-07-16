<template lang="html">
  <ul class="card-list">
    <li v-for="card in cardList">
      <card :card-id="card"></card>
    </li>
  </ul>
</template>

<style lang="less">
  .card-list {
    list-style: none;
    position: relative;
  }

  .card-list li {
    // margin: 40px;
    float: left;
  }
</style>

<script type="text/javascript">
  import Card from './card.vue';
  export default {
    props: ['self'],
    components: {
      Card
    },
    computed: {
      cardList: function() {
        // 此处get cardList
        return [1, 2, 3, 4];
      }
    }
  };
</script>
