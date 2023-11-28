<template>
  <div class="myDiv">
    <canvas ref="canvas" width="500" height="500"></canvas>
    <button @click="rotate">구군 룰렛돌리기</button>
  </div>
</template>

<style scoped>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");

* {
  font-family: Pretendard;
}

body {
  background: #f7f8fc;
}

canvas {
  transition: 2s;
}

button {
  background: #febf00;
  margin-top: 1rem;
  padding: 0.8rem 1.8rem;
  border: none;
  font-size: 1.5rem;
  font-weight: bold;
  border-radius: 5px;
  transition: 0.2s;
  cursor: pointer;
}

button:active {
  background: #444;
  color: #f9f9f9;
}

div {
  width: 500px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: relative;
}

div::before {
  content: "";
  position: absolute;
  width: 10px;
  height: 50px;
  border-radius: 5px;
  background: #000;
  top: -20px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 22;
}
</style>



<script>
import axios from "axios"
import { mapState,mapActions,mapMutations} from "vuex";
const planStore = "planStore";
export default {
    computed:{
    ...mapState(planStore,["randomplan"]),
    ...mapMutations(planStore,["SET_RANDOM_PALN"])
  },
  props:{
    data:Array,
    data2:Number
  },
  components:{
    
  },
  created(){

  },
  watch: {
      data() {
        this.product=[];
        console.log(this.product)
        this.num=[];
        this.operation=0;
        // for (let index = 0; index < this.data.length; index++) {
        //   console.log("나오새요 ~t"+index+" "+this.data[index].gugun_name);
        // }
        for (let index = 0; index < this.data.length; index++) {
          this.product.push(this.data[index].gugun_name);
          this.num.push(this.data[index].gugun_code);
        }
        // for (let index = 0; index < this.data.length; index++) {
        //   console.log("??????",this.product)
        // }
        this.newMake()
        this.operation=this.data2;
        console.log("길이를 보여줘"+this.product.length+" "+this.num.length);
      }
  },
  mounted() {
  },
   data() {
    return {
      product: [],
      num: [],
      operation:0
    };
  },
  methods: {
    ...mapActions(planStore,["setRandomPlan","setSidoGugun","setSidoGugunSub"]),
    newMake() {
      console.log(this.data);
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext("2d");
      const colors = [
        "#dc0936",
        "#e6471d",
        "#f7a416",
        "#efe61f",
        "#60b236",
        "#209b6c",
        "#169ed8",
        "#3f297e",
        "#87207b",
        "#be107f",
        "#e7167b",
        "#dc0936",
        "#e6471d",
        "#f7a416",
        "#efe61f",
        "#60b236",
      ];

      const [cw, ch] = [canvas.width / 2, canvas.height / 2];
      const arc = Math.PI / (this.product.length / 2);

      for (let i = 0; i < this.product.length; i++) {
        ctx.beginPath();
        ctx.fillStyle = colors[i % (colors.length - 1)];
        ctx.moveTo(cw, ch);
        ctx.arc(cw, ch, cw, arc * (i - 1), arc * i);
        ctx.fill();
        ctx.closePath();
      }

      ctx.fillStyle = "#fff";
      ctx.font = "18px Pretendard";
      ctx.textAlign = "center";

      for (let i = 0; i < this.product.length; i++) {
        const angle = arc * i + arc / 2;

        ctx.save();

        ctx.translate(
          cw + Math.cos(angle) * (cw - 50),
          ch + Math.sin(angle) * (ch - 50)
        );

        ctx.rotate(angle + Math.PI / 2);

        this.product[i].split(" ").forEach((text, j) => {
          ctx.fillText(text, 0, 30 * j);
        });

        ctx.restore();
      }
    },
rotate() {
  const canvas = this.$refs.canvas;
  canvas.style.transform = "initial";
  canvas.style.transition = "initial";

  setTimeout(() => {
    let ran = Math.floor(Math.random() * this.product.length);
    if (isNaN(this.num[ran]) || typeof this.num[ran] === 'undefined') {
      alert("죄송해요 다시 돌려주세요");
      return;
    }
    const arc = Math.floor(360 / this.product.length);
    const rotate = ran * arc + arc / 2 + 90;
    //const arc = 360 / this.product.length;
    console.log("src : ",arc);
    //const rotate =ran * arc + arc / 2; 
    canvas.style.transform = `rotate(-${rotate}deg)`;
    canvas.style.transition = "2s";
    console.log(this.data2);
    console.log(this.num[ran]);
    console.log(this.product[ran]);
    setTimeout(async () => {
      const response = await axios.get(`http://localhost:9999/trip/attract/random/${this.data2}/${this.num[ran]}`);
      console.log("???",this.num[ran]);
      this.setSidoGugun(this.data2);
      this.setSidoGugunSub(this.num[ran]);
      console.log(response.data);
      this.setRandomPlan(response.data);
    }, 2000);
  }, 10);
    },
  },
};
</script>