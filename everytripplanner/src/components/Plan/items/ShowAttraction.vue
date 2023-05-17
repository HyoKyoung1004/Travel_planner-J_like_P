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
    <div>
      <h2>B-card List</h2>
      <ul>
        <li v-for="card in bcards" :key="card.id" v-on:click="move(card)">
            <div >
                {{ card.content_id }}
                {{ card.userId }}
                {{ card.wishId }}
            </div>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import {mapState } from 'vuex';
  
  export default {
    computed: {
            ...mapState(['bcards','moveOn']),
    },
    methods: {
    }
  };
  </script>