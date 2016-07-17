<template lang="html">
  <div  v-if="show" class="card" @click="selectTheCard()">
    <span class="card-type">
      {{cardInfo.type}}
    </span>
    <div class="card-name">
      {{cardInfo.name}}
    </div>
    <div class="card-description">
      {{cardInfo.description}}
    </div>
    <span class="card-cost-hp">
      {{cardInfo.hp}}
    </span>
    <span class="card-cost-gp">
      {{cardInfo.gp}}
    </span>
  </div>
</template>

<script type="text/javascript">
  export default {
    props: ['cardId', 'cardInHand', 'needUseCard', 'cardList', 'socket'],
    data() {
      return {
        show: true
      };
    },
    computed: {
      canSelected: function() {
        let status = this.cardInHand[this.cardId] === '1';
        return this.needUseCard && status;
      },
      cardInfo: function() {
        return this.cardList[this.cardInHand];
      }
    },
    methods: {
      selectTheCard() {
        // 发牌操作
        if (this.canSelected) {
          this.show = false;
          console.log(this.cardId);
          this.socket.emit('select', this.cardId);
          return this.cardId;
        } else {
          return false;
        }
      }
    }
  };
</script>

<style lang="less">
@border-color: #000;
@card-height: 175px;
@card-width: 120px;
@card-name-height: 60px;
@card-cost-size: 20px;
@offset: 3px;
.card {
  border: 1px solid @border-color;
  height: @card-height;
  width: @card-width;
  padding: @offset;
  position: relative;
  transition: top 0.3s;
  top: 0;
  &:hover {
    top: -20px;
  }
}
// .card.small {
//   height: @card-height * 0.8;
//   width: @card-width * 0.8;
// }
.card-type {
  float: left;
  position: absolute;
}
.card-name {
  text-align: center;
  line-height: @card-name-height;
  font-size: 1.2em;
  font-weight: bolder;
}
.card-description {
  position: absolute;
  top: 50px;
  left:@offset;
}
.card-cost-hp {
  position: absolute;
  height: @card-cost-size;
  width: @card-cost-size;
  bottom: @offset;
  left: @offset;
  text-align: center;
  line-height: @card-cost-size;
}
.card-cost-gp {
  position: absolute;
  height: @card-cost-size;
  width: @card-cost-size;
  bottom: @offset;
  right: @offset;
  text-align: center;
  line-height: @card-cost-size;
}
.select-card-transition {
  top: -20px;
}
</style>
