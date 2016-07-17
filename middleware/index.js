var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var websocketServer = require('ws').Server;
var wss = new websocketServer({ port: 8888 });
var ws = null;
var counter = 0;
var gameRoom = io.of('/game-room');
var socketArr = [];
var playerIdList = [0, 1, 2, 3];
var message = null;

function findSocket(socketArr, elem) {
  for (let i = 0; i < socketArr.length; i++) {
    if (socketArr[i].id === elem) {
      return i;
    }
  }
}

wss.on('connection', function(_ws) {
  ws = _ws;
  ws.on('message', function(_message) {
    // console.log('received: %s', message);
    message = JSON.parse(_message);
  });
  ws.send('{"type": "initialize"}');
})

server.listen(3000);

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});
gameRoom.on('connection', function(socket){
  var playerId = null;
  // io.to('game').emit('news');
  // socket.broadcast.emit('init','123');
  //
  // socket.on('init', function(message) {
  //   socket.broadcast.to(socket.id).emit('postId', message);
  // });
  //
  // socket.on('whetherStartGame', function(message) {
  //   socket.broadcast.emit('startGame',  ? true : false)
  // })

  if (counter < 4) {
    counter++;
  }

  if (counter <= 4) {
    socketArr.push(socket);
    playerId = playerIdList.shift();
    socket.emit('getPlayerId', playerId);
    console.log(socket.id, counter, playerId);
  } else {
    return;
  }

  if (counter === 4) {
    console.log('开始游戏');
    socket.emit('start', '开始游戏');
    socket.broadcast.emit('start', '开始游戏');
  }

  socket.on('ready', function() {
    // TODO:此处请求服务器初始化(get card list)
    ws.send(JSON.stringify({operator: 0}));
    // var data = message;
    var data = [{name: 'hello', type: '养成', description: '1', hpchange: 1, gpchange: 1}];
    socket.emit('readyOK', data);
    ws.send(JSON.stringify({operator: 1}));
  });

  socket.on('getCard', function() {
    // TODO:此处从后端获得目前所有用户卡的数值
    // var data = message;
    var data = {
      type: 'message',
      content: {
        cardInHand: [
          [
            {cardId: 0, status: 1},
            {cardId: 0, status: 1},
            {cardId: 0, status: 1}
          ],
          [
            {cardId: 0, status: 1},
            {cardId: 0, status: 1},
            {cardId: 0, status: 1}
          ],
          [
            {cardId: 0, status: 1},
            {cardId: 0, status: 1},
            {cardId: 0, status: 1}
          ],
          [
            {cardId: 0, status: 1},
            {cardId: 0, status: 1},
            {cardId: 0, status: 1}
          ]
        ]
      }
    }
    var msg = data.content;
    socket.emit('getCardList', msg);
    if (playerId !== nextPlayer) {
      socket.emit('lockOperator');
    } else {
      socket.emit('unlockOperator');
    }
  });

  socket.on('select', function(id) {
    // TODO:让后端拿走id
    ws.send(JSON.stringify({operator: id}));
  });

  socket.on('disconnect', function () {
    if (playerId !== null) {
      counter--;
      let elemId = findSocket(socketArr, socket.id);
      socketArr.splice(elemId, 1);
      playerIdList.push(playerId);
      console.log('leave',playerId);
    }

  });
});
// nsp.emit('hi', 'everyone!');
// io.on('connection', function (socket) {
//   // console.log('hello');
//   counter++;
//   if (counter !== 4) {
//     return;
//   }
//   socket.emit('news', { hello: 'world' });
//   socket.on('my other event', function (data) {
//     console.log(data);
//   });
//
//   socket.on('my other event', function (data) {
//     console.log(data, 1);
//   });
// });
