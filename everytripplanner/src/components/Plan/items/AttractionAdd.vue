<!-- <template>
    <div class="drop-zone" @drop="dropItem" @dragover.prevent>
      <h2>다른 컴포넌트</h2>
      <div v-for="item in droppedItems" :key="item.id">
        {{ item.text }}
      </div>
    </div>
  </template>
  
  <script>
  import { mapState, mapMutations } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['droppedItems'])
    },
    methods: {
      ...mapMutations(['moveItem']),
          dropItem(event) {
                console.log("난 받았어");
      const itemId = parseInt(event.dataTransfer.getData('text/plain'), 10);
      const targetId = null; // 이동하고자 하는 타겟 아이템의 ID

      this.moveItem({ itemId, targetId });
    }
    }
  };
  </script>
  
  <style scoped>
  .drop-zone {
    width: 200px;
    height: 200px;
    background-color: red;
  }
  </style> -->
  <template>
    <div class="drop-zone">
      <h2>다른 컴포넌트</h2>
      <div v-for="item in droppedItems" :key="item.id">
        하하하하
        {{ item.text }}
      </div>
    </div>
  </template>
  
  <script>
  import { mapState } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['droppedItems'])
    }
  };
  </script>
  
  <style scoped>
.drop-zone {
  width: 200px;
  height: 200px;
  background-color: red;
}
.drop-zone.valid-drop {
  background-color: green;
}
  </style>