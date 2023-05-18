<!-- <template>
    <div>
      <div v-for="item in items" :key="item.id" draggable="true" @dragstart="dragStart(item, $event)">
        {{ item.text }}
      </div>
      <div class="drop-zone" @drop="dropItem" @dragover.prevent></div>
    </div>
  </template>
  
  <script>
  import { mapState, mapMutations } from 'vuex';
  
  export default {
    computed: {
      ...mapState(['items'])
    },
    methods: {
      ...mapMutations(['moveItem']),
          dragStart(item, event) {
                console.log("asdasd");
        event.dataTransfer.setData('text/plain', item.id);
      },
      dropItem(event) {
        const itemId = parseInt(event.dataTransfer.getData('text/plain'), 10);
        const targetId = null; // 이동하고자 하는 타겟 아이템의 ID
  
        this.moveItem({ itemId, targetId });
      }
    }
  };
  </script> -->
  <template>
    <div style=" overflow: scroll">
      <h2>B-card List</h2>
      <ul style="list-style-type:none;">
        <li v-for="card in bcards" :key="card.id">
          <b-card
              :title="String(card.content_id)"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 10rem;"
              class="mb-2">
              <b-card-text>
                {{ card.userId }} {{ card.wishId }}
              </b-card-text>
              <b-button v-on:click="move(card)" variant="primary">추가하기</b-button>
          </b-card>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import {mapState } from 'vuex';
  
  export default {
    data(){
      return{
        items:{
          type:Array,
          required: true
        }
      }
    },
    computed: {
            ...mapState(['bcards','moveOn']),
    },
    methods: {
      move(card){
        const cardObj = [
          this.content_id= card.content_id,
          this.userId= card.userId,
          this.wishId= card.wishId
      ];
        
        this.$emit("mom",cardObj);
      }
    }
  };
  </script>