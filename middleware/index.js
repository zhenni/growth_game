var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var websocketServer = require('ws').Server;
var wss = new websocketServer({ port: 8888 });

wss.on('connection', function(ws) {
  ws.on('message', function(message) {
    console.log('received: %s', message);
  });
  ws.send('{"type": "initialize"}');
})

server.listen(3000);

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});

io.on('connection', function (socket) {
  console.log('hello');
  socket.emit('news', { hello: 'world' });
  socket.on('my other event', function (data) {
    console.log(data);
  });
});
