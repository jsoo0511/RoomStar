<template>
  <div></div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return { room_id: 1 };
  },

  created() {
      axios
        .get("http://70.12.247.115:8080/get_room_by_room_id/"+ this.room_id)
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.error(error);
        });

      
  }

  // 방정보 받아오는 로직

  // 처음 create 됐을때 방 정보를 가져오고,
  // 방 정보에서
  //http://70.12.247.115:8080/get_room_by_room_id/2
  //get
};
</script>