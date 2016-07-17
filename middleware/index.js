var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var websocketServer = require('ws').Server;
var wss = new websocketServer({ port: 8888 });
var ws = null;
var counter = 0;
var gameRoom = io.of('/game-room');
var socketArr = [];

function findSocket(socketArr, elem) {
  for (let i = 0; i < socketArr.length; i++) {
    if (socketArr[i].id === elem) {
      return i;
    }
  }
}

wss.on('connection', function(_ws) {
  ws = _ws;
  ws.on('message', function(message) {
    console.log('received: %s', message);
  });
  ws.send('{"type": "initialize"}');
})

server.listen(3000);

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});
gameRoom.on('connection', function(socket){
  counter++;
  socketArr.push(socket);
  console.log(socket.id, counter);
  // io.to('game').emit('news');
  socket.broadcast.emit('news','123');

  socket.on('getId', function(message) {
    socket.broadcast.to(socket.id).emit('postId', message);
  })

  if (counter === 4) {
    console.log('开始游戏');
    socket.broadcast.emit('init', '开始游戏');
  }

  socket.on('disconnect', function () {
    counter--;
    let elemId = findSocket(socketArr, socket.id);

    socketArr.splice(elemId, 1);

    console.log('leave',counter);
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
