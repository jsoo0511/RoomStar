var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server);
var port = 3030;

let rooms = [null,null,null,null,null];

server.listen(port, function() {
    console.log('socket io server listening on port '+port);
})

//connection event handler
io.on('connection' , function(socket) {

    console.log('Connect from Client: '+ socket.id)

    console.log(socket.handshake.query);
    let room_id = socket.handshake.query.room_id;
    let user_id = socket.handshake.query.user_id;
    let user_identification = socket.handshake.query.user_identification;
    let player_idx = socket.handshake.query.player_idx;

    console.log("새로운 입장 : "+room_id,user_id);
    let room = rooms[room_id];
    //let watcher_cnt = 0;

    let watchers = [null,null,null,null,null];

    if(room){
        //방이 존재 할때
        if (user_identification=="singer"){
            console.log(room.singer1)
            //노래 부르는 사람일때
            if (room.singer1){
                console.log("여기는 싱어1이 있을때 ")
                room.singer2=user_id;
            }else {
                room.singer1=user_id;
            }
            room.singer_num = 2;
        }else if (user_identification=="watcher"){//시청자일 때,
            
            if (room.watcher_cnt>=6){
              return;
            }
            
            room.watchers[room.watcher_cnt] = user_id;
            room.watcher_cnt++;
        }
        room.sockets.push(socket);

    } else{
        //방이 존재하지 않을 때,
        console.log("방이 존재x")
        if (user_identification=="singer"){
            room = {
                singer1: user_id,
                singer2: null,
                watcher_cnt: 0,
                watchers: [],
                sockets:[socket],
                singer_num:1
            }   
        }else{
            //시청자일 때,
            room = {
                singer1: null,
                singer2: null,
                watcher_cnt: 1,
                watchers: [user_id],
                sockets:[socket],
                singer_num:0
            }   
        }
            rooms[room_id] = room;
    }
    socket.join(room_id);
    console.log(rooms);

    socket.to(room_id).broadcast.emit('join',{
        user_id: user_id,
        user_identification: user_identification,
        watcher_cnt: room.watcher_cnt,
        watchers: watchers,
        player_idx: player_idx
    });


    //chat
    socket.on('chat', function(data){
        console.log('message from Client: '+data.message)
        socket.broadcast.emit('chat', data);
    });

    //실시간 투표
    socket.on('VOTE_SELECTED', function (data) {
        console.log("get vote change")
        socket.emit('VOTE_SELECTED', data)
        //io.emit('GET_VOTE_SELECTED', data)
        console.log('server', data)
    });


    //음악 선택
    socket.on('SEND_MUSIC_SELECTED', function(data) {
        socket.emit('GET_MUSIC_SELECTED', data)
        io.emit('GET_MUSIC_SELECTED', data)
        console.log('server', data)
    });


    //webRTC
    socket.on('message', data =>{
       console.log("message in", data.message.type);
       
        
        socket.broadcast.emit('message', data);
    });


    //gameroom 나가기
    socket.on('leave', data =>{
        console.log("leaveeeeeeeeeeeeee ",data);
        
        let room_id = data.room_id;
        let room = rooms[room_id];
        let socket_id = socket.id;

        if (data.user_identification=="singer"){
            if (room.singer_num==1){
                rooms[room_id] = null;
                console.log('room destroy');
            }else{
                room.singer_num = 1;
                if (data.player_idx==0){
                    singer1 = null;
                }else{
                    singer2 = null;
                }
                io.sockets.to(room_id).emit('leave',data);
                room.sockets = room.sockets.filter(socket => (socket.id != socket_id))
            }
        }else{
            room.watchers = room.watchers.filter(watcher => (watcher != data.user_id))
            room.sockets = room.sockets.filter(socket => (socket.id != socket_id))
            room.watcher_cnt--;
            io.sockets.to(room_id).emit('leave',data);
        }
        console.log(rooms);
     });

})

