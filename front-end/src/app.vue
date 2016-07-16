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
        <div class="layout-stageArea">
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
        <card-list></card-list>
      </div>
    </div>
  </div>
</template>

<script>
  import AvatarList from './components/avatar/avatar-list.vue';
  import CardList from './components/card/card-list.vue';
  import io from 'socket.io-client';

  const socket = io('http://localhost:3000/game-room');
  socket.on('news', (data) => {
    console.log(data);
    socket.emit('my other event', { my: 'data' });
  });

  export default {
    name: 'app',
    data() {
      return {
        socket: socket
      };
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
