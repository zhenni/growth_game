<template>
  <!-- <h1>front-end</h1>
  <p>A vue project.</p> -->
  <!-- <avatar-list :self="0"></avatar-list> -->
  <div class="layout-total">
    <div class="layout-mainPlayArea">
      <div class="layout-otherPlayer">
        <avatar-list :self="0" :my-avatar="false"></card-list>
      </div>
      <div class="layout-battleArea">
        <div class="layout-stageArea" :load="emitInit()">
          {{stage}} {{playerId}}
        </div>
        <div class="layout-linkArea">
          <card-list></card-list>
        </div>
      </div>
    </div>
    <div class = "layout-footArea">
      <div class="layout-myPicture">
        <avatar-list :self="0" :my-avatar="true"></card-list>
      </div>
      <div class="layout-cardArea">
        <card-list :need-use-card="needUseCard" :></card-list>
      </div>
    </div>
  </div>
</template>

<script>
  import AvatarList from './components/avatar/avatar-list.vue';
  import CardList from './components/card/card-list.vue';
  import io from 'socket.io-client';

  // const socket = ;
  // var stage1 = '等待其他玩家接入';

  // socket.on('init', (data) => {
  //   // console.log()
  //
  // });

  export default {
    name: 'app',
    data() {
      return {
        socket: io('http://localhost:3000/game-room'),
        stage: '等待开始游戏',
        playerId: null,
        stageList: [
          '等待开始游戏',
          '抽牌阶段',
          '发牌阶段',
          '养成阶段',
          '连锁阶段',
          '清算阶段'
        ],
        allCard: [

        ],
        cardList: [

        ],
        hp: [],
        gp: [],
        yourturn: false,
        needUseCard: false,
        needSetPerson: false,
        nextPlayer: null,
        nextOperator: null,
        clearDesk: false,
        desk: []
      };
    },
    computed: {

    },
    methods: {
      giveUp: function() {
        return -1;
      },
      uploadStage: function(stage) {
        this.stage = stage;
      },
      initStage: function() {
        this.stage = '等待其他用户';
      },
      emitInit: function() {
        // this.socket = ;
        // console.log('hello13');
        // this.socket.manager('connect', (data) => {
        //   this.uploadStage('开始游戏');
        //
        // })
        // socket.emit('hello', message);
        // this.socket.emit('ready');
        this.socket.on('readyOK', (data) => {
          this.allCard = data;
          this.socket.emit('getCard');
        });

        this.socket.on('getPlayerId', (id) => {
          this.playerId = id;
        });

        this.socket.on('lockOperator', (id) => {
          this.yourturn = false;
          this.needUseCard = false;
          this.needSetPerson = false;
        });

        this.socket.on('unlockOperator', (id) => {
          this.yourturn = true;
        });

        this.socket.on('getCardList', (data) => {
          this.cardList = data.cardInHand;
          this.hp = data.hp;
          this.gp = data.gp;
          this.clearDesk = data.clearDesk;

          if (this.clearDesk === '1') {
            this.desk.forEach(()=> {
              this.desk.pop();
            });
          }

          switch (data.nextOperator) {
            case 1:
              this.stage = '抽卡阶段';
              break;
            case 2:
              this.needUseCard = true;
              this.stage = '发牌阶段';
              break;
            case 3:
              this.stage = '指定人';
              this.needSetPerson = true;
              break;
            case 4:
              this.stage = '养成阶段';
              this.needUseCard = true;
              break;
            case 5:
              this.stage = '连锁阶段';
              this.needUseCard = true;
              break;
            case 6:
              this.stage = '清算阶段';
              this.needUseCard = false;
              this.needSetPerson = false;
              break;
            default:
              this.needUseCard = false;
              this.needSetPerson = false;
          };
        });

        this.socket.on('start', (data) => {
          // socket.emit('my other event', { my: 'data' });
          this.uploadStage('开始游戏');
          this.socket.emit('ready');
          // console.log('hello');
          // this.roundCounter++;
          // if (this.roundCounter % 4 === 1) {
          //
          // }
        });
      }
    },
    components: {
      AvatarList,
      CardList
    }
  };
</script>
<style lang="less">
  @color: #aaa;
  html, body {
    background: @color;
    box-sizing: border-box;
    height: 100%;
  }

  *,
  *:before,
  *:after {
    box-sizing: inherit;
    padding: 0;
    margin: 0;
  }

  .layout-total{
    height: 100%;
    background-color:grey;
    margin:none;
  }

  .layout-mainPlayArea
  {
    height:66%;
    width:90%;
    position:relative;
    top:2%;
    left:5%;
    background-color:red;
    margin:0px 0px 0px 0px;
  }

  .layout-footArea
  {
    height:33%;
    width:90%;
    position: relative;
    left:5%;
    top:0%;
    background-color:blue;
  }

  .layout-myPicture{
    height:90%;
    width:18%;
    position: relative;
    left:2%;
    top:5%;
    background-color:pink;
  }

  .layout-otherPlayer{
    height:90%;
    width:18%;
    position: absolute;
    left:2%;
    top:5%;
    background-color:white;
  }

  .layout-battleArea{
    height:90%;
    width:75%;
    margin-left:22%;
    position:absolute;
    top:5%;
    background-color:black;
  }

  .layout-stageArea{
    height:20%;
    width:100%;
    position:absolute;
    top:0px;
    background-color:purple;
  }

  .layout-linkArea{
    height:80%;
    width:100%;
    position:absolute;
    bottom:0px;
    background-color:green;
  }

  .layout-cardArea{
    height:90%;
    width:75%;
    margin-left:22%;
    position:absolute;
    top:5%;
    background-color:yellow;
  }
</style>
