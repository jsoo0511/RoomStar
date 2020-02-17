var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server);
var port = 8088;

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

    console.log("didiidi"+room_id,user_id);
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
        }else if (user_identification=="watcher"){//시청자일 때,
            
            if (room.watcher_cnt>=6){
              return;
            }
            
            room.watchers[room.watcher_cnt] = user_id;
            room.watcher_cnt++;
            console.log(room.watcher_cnt);
            console.log(room.watchers);
            //room.watcher_cnt += 1;
        }
        
        // room
        //     .members
        //     .push(user_id);

        room.sockets.push(socket);

        
        //user_num = room.member_cnt;
        //user = 'user ' + user_num;

    } else{
        //방이 존재하지 않을 때,
        console.log("방이 존재x")
        if (user_identification=="singer"){
            room = {
                singer1: user_id,
                singer2: null,
                watcher_cnt: 0,
                watchers: {},
                sockets:[socket]
            }   
        }else{
            //시청자일 때,
            room = {
                singer1: null,
                singer2: null,
                watcher_cnt: 1,
                watchers: [user_id],
                sockets:[socket]
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

        var rtnMessage = {
            message: data.message
        };

        // 클라이언트에게 메시지를 전송한다
        socket.broadcast.emit('chat', rtnMessage);
    });




    //webRTC
    socket.on('message', data =>{
       console.log("message in", data.message.type);
       
        
        socket.broadcast.emit('message', data);
    });


})

