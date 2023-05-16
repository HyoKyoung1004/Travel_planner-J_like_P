<template>
  <div>
    <div class="js_tabs v-tabs">
      <ul class="tabs"></ul>
      <div class="panels pt0">
        <div id="tabCon01" class="panel area_point selected">
          <div class="js_tabs place_list">
            <ul class="tabs">
              <!-- <b-button variant="danger" @click="openModalSeoul">서울</b-button> -->
            </ul>
            <div class="panels place_scroll">
              <div id="place01" class="panel selected">
                <seoul-view
                  v-bind:openModalSeoul="openModalSeoul"
                  v-if="openSeoul == true"
                />
                <gyeongi-view
                  v-bind:openModalGyeonggi="openModalGyeonggi"
                  v-if="openGyeonggi == true"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>