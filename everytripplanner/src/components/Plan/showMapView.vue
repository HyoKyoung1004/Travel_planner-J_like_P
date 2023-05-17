<template>
    <div id="map1">
    
    </div>
    
</template>

<script>
export default {
  name: "KaKaoMap",
    components: {

  },
  setup() {},

  data() {
    return {
      message: "",
      map: null,
      value: "",
      context: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  created() {},
  unmounted() {},
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=be3e537e27a33c775736b78a875b8b88&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      this.loadMaker();
    },

    loadMaker() {
      const markerPosition = new window.kakao.maps.LatLng(
        33.450701,
        126.570667
      );

      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
    onContext(ctx) {
      this.context = ctx;
    },
  },
};
</script>

<style scoped>
</style>