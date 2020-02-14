var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server);
// var express = require('express');
// var http = require('http');

// var express = require('express')
// var app = express()
// var http = require('http').Server(app)
// http.listen(8210, () => {
//     console.log("Server Running at http://127.0.0.1:8210")
// })
const cors = require('cors')
app.use(cors())
//setting cors 
// app.all('/*', function(req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Headers", "X-Requested-With");
//     //res.status(405).send();
//     next();
// });

// app.get('/', function(req, res) {
//     res.sendFile('Hellow Chating App Server');
// });
let rooms = {};

//connection event handler
io.on('connection' , function(socket) {

    console.log('Connect from Client: '+ socket.id)

    let room_id = socket.handshake.query.room_id;
    let user_id = socket.handshake.query.user_id;
    let user_identification = socket.handshake.query.user_identification;

    let room = rooms.room_id;

    let user_num;

    if(room){
        if (user_identification=="singer"){
            if (room.singer_cnt>=2){
                
            }
        }else{
            if (room.watcher_cnt>=6){
                        return;
                    }
        }
        
        room
            .members
            .push(user_id);

        room
            .sockets
            .push(socket);

        room.member_cnt += 1;
        user_num = room.member_cnt;
        //user = 'user ' + user_num;

    }

    socket.broadcast.emit('join')

    socket.on('chat', function(data){
        console.log('message from Client: '+data.message)

        var rtnMessage = {
            message: data.message
        };

        // 클라이언트에게 메시지를 전송한다
        socket.broadcast.emit('chat', rtnMessage);
    });

    socket.on('message', data =>{
       // console.log("message in")
        
        socket.broadcast.emit('message', data);
    });


})

server.listen(3001, function() {
    console.log('socket io server listening on port 3001')
})

