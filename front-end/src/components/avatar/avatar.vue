<template lang="html">
  <div class="avatar-img">
    <span class="avatar-hand-card-number" @click="getUserInfo()">
      {{playerInfo.playerCardNumber}}
    </span>
    <span class="avatar-hp">
      {{playerInfo.playerHp}}
    </span>
    <span class="avatar-gp">
      {{playerInfo.playerGp}}
    </span>
  </div>
</template>

<script type="text/javascript">
  export default {
    // 从父节点传入playerID
    props: ['playerId'],
    data() {
      return {
        playerInfo: {
          playerId: this.playerId,
          avatarImg: 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSiJV0-9CnKJEusu-TPABhCqNX2vw4PJWw49-wUac9FqLuCM4RYxg',
          playerCardNumber: 0,
          playerHp: 0,
          playerGp: 0
        }
      };
    },
    methods: {
      selectUser() {
        if (this.userSelected) {
          this.socket.emit('select', this.playerId);
        } else {
          return false;
        }
      },
      getUserInfo() {
        // 这里需要传输userId:
        // 发送: userId
        // 获取:
        // userInfo
        let userInfo = {
          avatarImg: 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSiJV0-9CnKJEusu-TPABhCqNX2vw4PJWw49-wUac9FqLuCM4RYxg',
          playerCardNumber: this.playerId,
          playerHp: 1,
          playerGp: 2
        };
        console.log('hello');
        this.playerInfo = userInfo;
      }

    }
  };
</script>

<style lang="less">
@border-color: #000;
@avatar-img-size: 100px;
@avatar-icon-size: 40px;
@icon-offset: @avatar-icon-size / 2;
.avatar-icon-params() {
  height: @avatar-icon-size;
  width: @avatar-icon-size;
  display: inline-block;
  position: absolute;
  background: red;
  line-height: @avatar-icon-size;
  text-align: center;
}
.avatar-img {
  border: 1px solid @border-color;
  height: @avatar-img-size;
  width: @avatar-img-size;
  display: inline-block;
  position: relative;
  border-radius: 5px;
  marign: 25px;
}
.big{
  height:150px;
  width:150px;
}
.avatar-hand-card-number {
  .avatar-icon-params();
  right: -@icon-offset;
  top: -@icon-offset;
}
.avatar-hp {
  .avatar-icon-params();
  bottom: -@icon-offset;
  left: -@icon-offset;
  border-radius: 100%;
}
.avatar-gp {
  .avatar-icon-params();
  bottom: -@icon-offset;
  right: -@icon-offset;
}


</style>
