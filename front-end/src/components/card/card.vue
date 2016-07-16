<template lang="html">
  <div  v-if="show" class="card" @click="selectTheCard()">
    <span class="card-type">
      养成
    </span>
    <div class="card-name">
      夭寿啦
    </div>
    <div class="card-description">
      喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵
    </div>
    <span class="card-cost-hp">
      1
    </span>
    <span class="card-cost-gp">
      1
    </span>
  </div>
</template>

<script type="text/javascript">
  export default {
    props: ['cardId'],
    data() {
      return {
        show: true
      };
    },
    methods: {
      selectTheCard() {
        // 发牌操作
        this.show = false;
        console.log(this.cardId);
      }
    }
  };
</script>

<style lang="less">
  @border-color: #000;
  @card-height: 200px;
  @card-width: 150px;
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
    //position: absolute;
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
