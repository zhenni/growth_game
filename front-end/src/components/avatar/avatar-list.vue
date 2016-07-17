<template lang="html">
  <ul class="other-player-list">
    <li v-for="player in otherPlayers">
      <avatar :player-id="player"></avatar>
    </li>
  </ul>
</template>

<style lang="less">
  .other-player-list {
    list-style: none;
    position: relative;
  }

  li {
    margin: 10px;
  }
</style>

<script type="text/javascript">
  import Avatar from './avatar.vue';
  export default {
    props: ['self', 'myAvatar'],
    components: {
      Avatar
    },
    computed: {
      otherPlayers: function() {
        let filter = (player) => {
          console.log(player, this.self);
          return player !== this.self;
        };

        return this.myAvatar ? [this.self] : [0, 1, 2, 3].filter(filter);
      }
    }
  };
</script>
