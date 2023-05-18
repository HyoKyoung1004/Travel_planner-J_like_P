<template>
  <div id="map1"></div>
</template>

<script>
export default {
  name: "KaKaoMap",
  components: {},
  setup() {},

  data() {
    return {
      message: "",
      map: null,
      value: "",
      context: null,
      coordinates: {
        latitude: 33.3617,
        longitude: 126.5292,
      },
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  created() {
    this.latitude = this.$route.params.lat;
    this.longitude = this.$route.params.lng;
    console.log(this.latitude, this.longitude);
    this.loadMap();
  },
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
        center: new window.kakao.maps.LatLng(this.coordinates.latitude, this.coordinates.longitude),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      this.loadMaker();
    },
    changeCoordinates() {
      const center = new window.kakao.maps.LatLng(
        this.coordinates.latitude,
        this.coordinates.longitude
      );
      this.map.setCenter(center);
    },
    loadMaker() {
      const markerPosition = new window.kakao.maps.LatLng(
        this.coordinates.latitude,
        this.coordinates.longitude
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

<style scoped></style>
