<template>
  <!-- <h1>front-end</h1>
  <p>A vue project.</p> -->
  <!-- <avatar-list :self="0"></avatar-list> -->
  <div class="layout-total" :load="emitInit()">
    <div class="layout-mainPlayArea">
      <div class="layout-otherPlayer">
        <avatar-list :self="0" :my-avatar="false"></card-list>
      </div>
      <div class="layout-battleArea">
        <div class="layout-stageArea">
          {{stage}}
        </div>
        <div class="layout-chainArea">
          <card-list></card-list>
          <input type="button" id="giveUp" class="layout-giveUpButton" value="giveUp">
        </div>
      </div>
    </div>
    <div class = "layout-footArea">
      <div class="layout-myPicture">
        <avatar-list :self="0" :my-avatar="true"></card-list>
      </div>
      <div class="layout-cardArea">
        <card-list></card-list>
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
        playerId: 3,
        roundCounter: 0,  // 回合数
        timesCounter: 0 // 轮数
      };
    },
    computed: {

    },
    methods: {
      uploadStage: function(stage) {
        this.stage = stage;
      },
      initStage: function() {
        this.stage = '等待其他用户';
      },
      emitInit: function() {
        // this.socket = ;
        console.log('hello13');
        // this.socket.manager('connect', (data) => {
        //   this.uploadStage('开始游戏');
        //
        // })
        // socket.emit('hello', message);
        this.socket.on('init', (data) => {
          // socket.emit('my other event', { my: 'data' });
          this.uploadStage('开始游戏');
//
          console.log('hello');
          this.roundCounter++;
          if (this.roundCounter % 4 === 1) {

          }
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

  html, body,div{
		height: 100%;
		overflow-x:hidden;
		overflow-y:hidden;
	}

	.layout-total{
		height:620px;
		width:1300px;
		margin: auto;
  	position: absolute;
  	top:0;left:0;bottom:0;right: 0;
		background-color:grey;
	}

	.layout-mainPlayArea
	{
		height:375px;
		width:1300px;
		margin: auto;
  	position: absolute;
  	top:0; left: 0; bottom:200px; right: 0;
		background-color:red;
	}

	.layout-footArea
	{
		height:200px;
		width:1300px;
		margin: auto;
  		position: absolute;
  		top:400px; left: 0; bottom:0; right: 0;
 		background-color:blue;
	}

	.layout-myPicture{
		height:200px;
		width:200px;
  	margin: auto;
  	position: absolute;
    text-align:center;
  	top:10px; left: 0px; bottom:10px; right:1050px;
 		background-color:pink;
    padding-top:10px;
	}

	.layout-otherPlayer{
		height:350px;
		width:150px;
  	margin: auto;
  	position: absolute;
    text-align:center;
  	top:10px; left: 0px; bottom:10px; right:1050px;
		background-color:white;
	}

	.layout-battleArea{
		height:350px;
		width:1000px;
  	margin: auto;
  	position: absolute;
  	top:10px; left:225px; bottom:10px; right:0px;
		background-color:black;
	}

	.layout-stageArea{
		height:75px;
    line-height:60px;
    width:950px;
  	margin: auto;
    text-align:center;
  	position: absolute;
  	top:0px; left:0px; bottom:250px; right:0px;
		background-color:purple;
	}

	.layout-chainArea{
		height:250px;
		width:950px;
    text-align:center;
  		margin: auto;
  		position: absolute;
  		top:50px; left:0px; bottom:0px; right:0px;
		background-color:yellow;
	}

	.layout-cardArea{
		height:200px;
		width:1000px;
  	margin: auto;
    text-align:center;
    padding-left:50px;
    padding-right:50px;
  	position: absolute;
  	top:0px; left:225px; bottom:0px; right:0px;
 		background-color:green;
	}

	.layout-giveUpButton{
		height:40px;
		width:100px;
  	position:absolute;
  	bottom:20px; right:20px;
 		background-color:orange;
 		color: #fff;
 		border:2px solid;
		border-radius:25px;
		outline: 0 none;
	}

  .layout-giveUpButton{
		height:40px;
		width:100px;
  	position:absolute;
  	bottom:10px; right:20px;
 		background-color:orange;
 		color: #fff;
 		border:2px solid;
		border-radius:25px;
		outline: 0 none;
	}

</style>
