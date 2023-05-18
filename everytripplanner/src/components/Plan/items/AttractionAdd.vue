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

    <div style=" overflow: scroll">
      <h2>B-card List</h2>
      <ul style="list-style-type:none;">
        <li v-for="car in card" :key="car.id">
          <b-card
              :title="String(car[0])"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 10rem;"
              class="mb-2">
              <b-card-text>
                {{ car[1] }} {{ car[2]}}
              </b-card-text>
          </b-card>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapState } from 'vuex';
  
  export default {
    props:{
        card:{
          type:Array,
          required:true
        }
    },
    computed: {
      ...mapState(['droppedItems'])
    },

  };
  </script>
  
  <style scoped>
  </style>