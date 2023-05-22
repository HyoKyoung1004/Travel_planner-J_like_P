<template>
<div>
  <div id="map" >
  </div>
</div>
</template>

<script>
import { mapState } from "vuex";
const planStore = "planStore";
export default {
  name: "KaKaoMap",
  components: {},
  setup() {},
  computed:{
    ...mapState(planStore, ["markersV"]),
    
  },
  data() {
    return {
      message: "",
      map: null,
      value: "",
      context: null,
      latitude: 33.3617,
      longitude: 126.5292,
      markers: [],
      position: []
    };
  },
  watch:{
    latitude(){
      console.log("??? :",this.latitude);
      this.$emit("lat",this.latitude);
    },
    longitude(){
      console.log("??? :",this.longitude);
      this.$emit("lng",this.longitude);
    },
    markersV(){
      this.setMarker();
    }
    
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      if (this.$route.query.clearMarkers) {
        this.clearMarkers();
      }
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
created() {
  if (window.kakao && window.kakao.maps) {
    if (this.$route.params.lat) {
      this.latitude = this.$route.params.lat;
      this.longitude = this.$route.params.lng;
    }
    this.loadMap();
  } else {
    this.loadScript();
  }
},

  unmounted() {},
  methods: {
    clearMarkers() {
    // 기존 마커들을 제거하는 로직을 구현하세요.
    // 예시:
    this.markers.forEach((marker) => {
      marker.setMap(null); // 각 마커를 지도에서 제거
    });
    this.markers = []; // 마커 배열 초기화
  },
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=be3e537e27a33c775736b78a875b8b88&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
  //   changeCoordinates(latlng) {
  //     console.log("changeCoordinates 메서드 실행됨");
  //   const center = new window.kakao.maps.LatLng(latlng.latitude, latlng.longitude);
  //   this.map.setCenter(center);
  // },
  loadMap() {
    
        const container = document.getElementById("map");
  if (!container) {
    console.error("Map container element not found");
    return;
  }
  
      
      const options = {
        center: new window.kakao.maps.LatLng(this.latitude, this.longitude),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      
      // var marker = new window.kakao.maps.Marker({
      //   position: this.map.getCenter(),
      // });
      // marker.setMap(this.map);

      // this.positions = [];
      // this.markersV.forEach((item) => {
      //   console.log(this.markersV);
      //   let obj = {};
      //   obj.latlng = new window.kakao.maps.LatLng(item.latitude, item.longitude);
      //   this.positions.push(obj);
      // });

    // 중심 좌표 변경 이벤트 리스너 등록
    new window.kakao.maps.event.addListener(this.map, 'center_changed', () => {
      //const level = this.map.getLevel();
      const latlng = this.map.getCenter();
      this.latitude=latlng.getLat();
      this.longitude=latlng.getLng();
    });
      //this.loadMaker();
    },
    // changeCoordinates() {
    //   console.log("????");
    //   const center = new window.kakao.maps.LatLng(
    //     this.latlng.latitude,
    //     this.latlng.longitude
        
    //   );
    //   this.map.setCenter(center);
    // },
    // loadMaker() {
    //   this.markers=[];
    //   this.positions.forEach((position)=>{
    //     const marker =new window.kakao.maps.Marker({
    //       map:this.map,
    //       position:position.latlng
    //     });
    //     this.markers.push(marker);

    //     const bounds = this.positions.reduce(
    //     (bounds, position) => bounds.extend(position.latlng),
    //     new window.kakao.maps.LatLngBounds()
    //   );
    //   this.map.setBounds(bounds);

    //   })
    //   const markerPosition = new window.kakao.maps.LatLng(
    //     this.latitude,
    //     this.longitude
    //   );

    //   const marker = new window.kakao.maps.Marker({
    //     position: markerPosition,
    //   });

    //   marker.setMap(this.map);
    // },
  //     setMarker() {
  //     if (this.markersV && this.markersV.length > 0) {
  //     this.positions = [];
  //     this.markersV.forEach((item) => {
  //       console.log(this.markersV);
  //       let obj = {};
  //       obj.latlng = new window.kakao.maps.LatLng(item.latitude, item.longitude);
  //       this.positions.push(obj);
  //     });
  //     this.positions.forEach((position)=>{
  //       console.log(position);
  //       const marker =new window.kakao.maps.Marker({
  //         map:this.map,
  //         position:position.latlng
  //       });
  //       this.markers.push(marker);

  //     const bounds = this.positions.reduce(
  //       (bounds, position) => bounds.extend(position.latlng),
  //       new window.kakao.maps.LatLngBounds()
  //     );
  //     this.map.setBounds(bounds);

  //     });
  //     const markerPosition = this.positions[0].latlng;

  //     const marker = new window.kakao.maps.Marker({
  //       position: markerPosition,
  //     });
  //     this.markers.push(marker);
  //     marker.setMap(this.map);
  //     }
  //   },
  //   onContext(ctx) {
  //     this.context = ctx;
  //   },
  setMarker() {
  if (this.markersV && this.markersV.length > 0) {
    this.positions = [];
    this.markersV.forEach((item) => {
      const position = new window.kakao.maps.LatLng(item.latitude, item.longitude);
      this.positions.push(position);

      const marker = new window.kakao.maps.Marker({
        position: position,
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    });

    const bounds = new window.kakao.maps.LatLngBounds();
    this.positions.forEach((position) => {
      bounds.extend(position);
    });

    this.map.setBounds(bounds);
  }
}

   },
};
</script>

<style scoped></style>
