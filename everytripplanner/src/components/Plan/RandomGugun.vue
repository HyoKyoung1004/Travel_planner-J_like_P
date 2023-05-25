<template>
  <div class="myDiv">
    <canvas ref="canvas" width="500" height="500"></canvas>
    <button @click="rotate">시도 룰렛돌리기</button>
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

export default {
  components:{
  },
  mounted() {
    this.newMake();
  },
   data() {
    return {
product: [
  '서울', // 0
  '인천', // 1
  '대전', // 2
  '대구', // 3
  '광주', // 4
  '부산', // 5
  '울산', // 6
  '세종특별자치시', // 7
  '경기도', // 8
  '강원도', // 9
  '충청북도', // 10
  '충청남도', // 11
  '경상북도', // 12
  '경상남도', // 13
  '전라북도', // 14
  '전라남도', // 15
  '제주도' // 16
],
num: [
  1,  // 0
  2,  // 1
  3,  // 2
  4,  // 3
  5,  // 4
  6,  // 5
  7,  // 6
  8,  // 7
  31, // 8
  32, // 9
  33, // 10
  34, // 11
  35, // 12
  36, // 13
  37, // 14
  38, // 15
  39  // 16
]
    };
  },
  methods: {
    newMake() {
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

  // ran 변수를 함수 스코프에 선언
  
  
  setTimeout(() => {
    let ran = Math.floor(Math.random() * this.product.length);
    console.log("ran",ran);
    if (isNaN(this.num[ran]) || typeof this.num[ran] === 'undefined') {
      alert("죄송해요 다시 돌려주세요");
      return;
    }
    const arc = 360 / this.product.length;
    // const rotate = ran * arc + 3600 - arc / 
    //ran * arc + 3600 + arc * 3 - arc / 4;
    const rotate = (ran+4) * arc + arc / 2; // 회전 각도 수정
    canvas.style.transform = `rotate(-${rotate}deg)`;
    canvas.style.transition = "2s";

    // canvas.style.transform = `rotate(-${rotate}deg)`;
    // canvas.style.transition = "2s";
    console.log("sido_code : "+ this.num[ran]);
    console.log("지역명 이름 : "+this.product[ran])
    setTimeout(async () => {
      const response = await axios.get("http://localhost:9999/trip/attract/random/" + this.num[ran]);
      console.log("제발요`~~~",response.data);
      this.$emit("momsido", response.data); 
      this.$emit("sidonum",this.num[ran]);
      // 여기서 response.data를 사용할 수 있음
    }, 2000);
  }, 10);
},

      
  },
};
</script>