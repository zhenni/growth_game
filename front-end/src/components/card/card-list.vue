<template lang="html">
  <ul class="card-list">
    <li v-for="card in cardList">
      <card :card-id="card" :card-in-hand="cardInHand" :need-use-card="needUseCard" :card-list="allCard" :socket="socket"></card>
    </li>
  </ul>
</template>

<style lang="less">
.card-list {
  list-style: none;
  position: relative;
}
.card-list li {
  margin:25px;
  float: left;
}
</style>

<script type="text/javascript">
  import Card from './card.vue';
  export default {
    props: ['self', 'allCard', 'deskOrNot', 'cardInHand', 'cardDesk', 'needUseCard', 'socket'],
    components: {
      Card
    },
    computed: {
      cardList: function() {
        // 此处get cardList
        if (this.deskOrNot) {
          return this.cardDesk;
        } else {
          return this.cardInHand;
        }
      }
    }
  };
</script>
