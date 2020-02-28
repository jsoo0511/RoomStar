<template>
  <div id="HomePage">
    <v-app light id="hp_video">
      <v-content>
        <section class="introduce">
          <section class="hero">
            <div class="hero__main">
              <div class="layer layer-displayed" data-scene="1">  <!-- 왼쪽 맨 위 사진-->
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/bh/bh_1.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="2">
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/hj/hj_1.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="3">,
                <div
                  class="layer-image-wrapper"
                   :style="{backgroundImage: 'url('+require('@/assets/images/jh/jh_1.jpg')+')'}">
              </div>
              </div>
               <div class="layer" data-scene="4">,
                <div
                  class="layer-image-wrapper"
                   :style="{backgroundImage: 'url('+require('@/assets/images/js/js_1.jpg')+')'}">
              </div>
              </div>
               <div class="layer" data-scene="5">,
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/sw/sw_1.jpg')+')'}">
              </div>
              </div>
              <div class="text">
                <div class="text__layers">
                  <span class="layer layer-displayed" data-scene="1">Bumhee Won</span>
                  <span class="layer" data-scene="2">Hajung Jung</span>
                  <span class="layer" data-scene="3">Jongho Lee</span>
                  <span class="layer" data-scene="4">JungSoo Lee</span>
                  <span class="layer" data-scene="5">Seungwon Chung</span>
                </div>
              </div>
            </div>
            <div class="hero__second">
              <div class="layer layer-displayed" data-scene="1"> <!-- 가장 오른쪽 사진-->
                <div
                  class="layer-image-wrapper"
                   :style="{backgroundImage: 'url('+require('@/assets/images/bh/bh_2.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="2">
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/hj/hj_2.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="3">
                <div
                  class="layer-image-wrapper"
                   :style="{backgroundImage: 'url('+require('@/assets/images/jh/jh_2.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="4">
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/js/js_1.jpg')+')'}">
              </div>
              </div>
              <div class="layer" data-scene="5">
                <div
                  class="layer-image-wrapper"
                  :style="{backgroundImage: 'url('+require('@/assets/images/sw/sw_2.jpg')+')'}">
              </div>
              </div>
            </div>
            <div class="hero__third">
              <div class="layer__wrapper">
                <div class="layer layer-displayed" data-scene="1">
                  <span></span>
                  <span>S/ Moda</span>
                </div>
                <div class="layer" data-scene="2">
                  <span></span>
                  <span>Muse Magazine</span>
                </div>
                <div class="layer" data-scene="3">
                  <span></span>
                  <span>Fashion Floor</span>
                </div>
                 <div class="layer" data-scene="4">
                  <span></span>
                  <span>Back-End</span>
                </div>
                 <div class="layer" data-scene="5">
                  <span></span>
                  <span>Muse Magazine</span>
                </div>
              </div>
              <button @click="switchLayer()">NEXT TALENT ></button>
              <!-- <button onclick="switchLayer(); return true;">NEXT TALENT ></button> -->
            </div>
            <div class="hero__footer">
              <div class="container">
                <div class="layer layer-displayed" data-scene="1"> <!--아래 왼쪽-->
                  <div
                    class="layer-image-wrapper"
                   :style="{backgroundImage: 'url('+require('@/assets/images/bh/bh_4.jpg')+')'}">
              </div>
                </div>
                <div class="layer" data-scene="2">
                  <div
                    class="layer-image-wrapper"
                    :style="{backgroundImage: 'url('+require('@/assets/images/hj/hj_3.jpg')+')'}">
              </div>
                </div>
                <div class="layer" data-scene="3">
                  <div
                    class="layer-image-wrapper"
                    :style="{backgroundImage: 'url('+require('@/assets/images/jh/jh_3.jpg')+')'}">
              </div>
                </div>
                 <div class="layer" data-scene="4">
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/js/js_1.jpg')+')'}">
              </div>
                </div>
                 <div class="layer" data-scene="5">
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/sw/sw_3.jpg')+')'}">
              </div>
                </div>
              </div>
              <div class="container">
                <div class="layer layer-displayed" data-scene="1"> <!--아래 오른쪽-->
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/bh/bh_5.jpg')+')'}">
              </div>
                </div>
                <div class="layer" data-scene="2">
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/hj/hj_4.jpg')+')'}">
              </div>
                </div>
                <div class="layer" data-scene="3">
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/jh/jh_4.jpg')+')'}">
              </div>
                </div>
                 <div class="layer" data-scene="4">
                  <div
                    class="layer-image-wrapper"
                     :style="{backgroundImage: 'url('+require('@/assets/images/js/js_1.jpg')+')'}">
              </div>
                </div>
                 <div class="layer" data-scene="5">
                  <div
                    class="layer-image-wrapper"
                    :style="{backgroundImage: 'url('+require('@/assets/images/sw/sw_5.jpg')+')'}">
              </div>
                </div>
              </div>
            </div>
            <div class="portrait">  <!-- 증명사진 부분-->
              <div
                class="layer layer-displayed"
                data-scene="1"
                :style="{backgroundImage: 'url('+require('@/assets/images/bh/bh_img.jpg')+')'}">
              </div>
              
              <div
                class="layer"
                data-scene="2"
                 :style="{backgroundImage: 'url('+require('@/assets/images/hj/hj_img.jpg')+')'}">
              </div>
              <div
                class="layer"
                data-scene="3"
                  :style="{backgroundImage: 'url('+require('@/assets/images/jh/jh_img.jpg')+')'}">
              </div>
               <div
                class="layer"
                data-scene="4"
                 :style="{backgroundImage: 'url('+require('@/assets/images/js/js_img.jpg')+')'}">
              </div>
               <div
                class="layer"
                data-scene="5"
                  :style="{backgroundImage: 'url('+require('@/assets/images/sw/sw_img.jpg')+')'}">
              </div>
            </div>
            <div class="line"></div>
          </section>
        </section>
      </v-content>
    </v-app>
  </div>
</template>

<script>
import $ from "jquery";
export default {
  name: "introduce",
  components: {},
  data() {
    return {
      hero: "",
      layers: [],
      sliderIndex: 1
    };
  },
  // 모든게 부착되고 나서 받아와야 하므로 mounted사용함
  mounted() {
    // console.log('--------', document.querySelectorAll('.layer.layer-displayed'))
    // console.log('===========', document.querySelectorAll('.layer'))
    this.layers = [...document.querySelectorAll('.layer')];
    console.log(this.layers)
    this.hero = document.querySelector('.hero');
  },
  
  computed: {
    heroSelector() {
      return document.querySelector(".hero");
    },
    layerSelector() {
      return document.querySelectorAll(".layer");
    }
  },
  methods: {
    outboundInput() {},
    switchLayer() {
      var step = 1;
      const nextSlide =
        (this.sliderIndex + step) % 5 === 0 ? 5 : (this.sliderIndex + step) % 5;
      console.log(this.layers);
      for (let i of this.layers) {
        i.classList.remove("layer-displayed");
        i.classList.remove("layer-displayed-exit");
        console.log(i);
        if (i.dataset.scene == nextSlide) {
          console.log(i);
          i.classList.add("layer-displayed");
        }
        if (i.dataset.scene == this.sliderIndex) {
          i.classList.add("layer-displayed-exit");
        }
      }
      console.log(nextSlide);
      this.sliderIndex = nextSlide;
    }
  }

  // mounted() {
  //   $(function switchLayer(step = 1) {
  //     console.log(step);
  //     const nextSlide =
  //       (this.sliderIndex + step) % 3 === 0 ? 3 : (this.sliderIndex + step) % 3;
  //     for (let i of this.layers) {
  //       i.classList.remove("layer-displayed");
  //       i.classList.remove("layer-displayed-exit");
  //       if (i.dataset.scene == nextSlide) {
  //         i.classList.add("layer-displayed");
  //       }
  //       if (i.dataset.scene == this.sliderIndex) {
  //         i.classList.add("layer-displayed-exit");
  //       }
  //     }
  //     this.sliderIndex = nextSlide;
  //   });
  // }
};
</script>

<style scoped lang="scss" type="text/scss">
@import url("https://fonts.googleapis.com/css?family=Playfair+Display:400,700");

:root {
  * {
    font-family: "Playfair Display", serif;
    transition: transform 1.5s cubic-bezier(0.55, 0, 0.1, 1),
      opacity 1.5s cubic-bezier(0.55, 0, 0.1, 1),
      font-size 1.5s cubic-bezier(0.55, 0, 0.1, 1),
      letter-spacing 1.5s cubic-bezier(0.55, 0, 0.1, 1);
    box-sizing: border-box;
    outline: none;
  }
}

#HomePage {
  height: 100vh;
  width: 100vw;

}

main {
  display: grid;
  grid-template-rows: 50px 100px auto;

  .menu {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;

    padding: 20px;
    z-index: 5;

    > *:last-child {
      text-align: right;
    }

    nav {
      display: flex;
      justify-content: space-between;

      a {
        text-decoration: none;
        color: black;
      }
    }
  }

  header {
    padding-left: 20px;

    h1 {
      font-size: 3rem;
    }
  }

  .hero {
    position: relative;
    display: grid;
    grid-template-rows: 2.5fr 1.5fr;
    grid-template-columns: 45% 33% 22%;
    grid-template-rows: 60% 40%;

    > * {
      position: relative;
      overflow: hidden;
    }

    &__main {
      grid-row: 1;
      grid-column: 1;

      > .layer {
        background-color: black;
        &-displayed {
          z-index: 1 !important;
          .layer-image-wrapper {
            transform: scale(1);
          }
        }

        &-displayed-exit {
          z-index: 2 !important;
          transform: translateY(-100%);

          .layer-image-wrapper {
            transform: scale(1.1) !important;
          }
        }
      }

      .text {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        width: 100%;
        height: 100%;

        z-index: 4;

        &__layers {
          position: relative;
          width: 100%;
          height: 100%;

          > .layer {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

            font-size: 3rem;
            letter-spacing: 5px;
            color: white;
            background-color: transparent;
            transform: translateY(10%);
            opacity: 0;

            &-displayed {
              font-size: 2rem;
              letter-spacing: 1px;
              transform: translateY(0%);
              opacity: 1;
            }

            &-displayed-exit {
              font-size: 1rem;
              letter-spacing: 1px;
              transform: translateY(-10%);
              opacity: 0;
            }
          }
        }
      }
    }

    &__second {
      grid-row: 1 / -1;
      grid-column: 2;

      .layer {
        background-color: black;
        &-displayed {
          z-index: 1 !important;
          .layer-image-wrapper {
            transform: scale(1);
          }
        }

        &-displayed-exit {
          z-index: 2 !important;
          transform: translateY(100%);

          .layer-image-wrapper {
            transform: scale(1.4) !important;
          }
        }
      }
    }

    &__third {
      grid-row: 1 / -1;
      grid-column: 3;

      display: grid;
      grid-template-rows: auto 50px;
      background-color: #ab8972;
      color: white;

      button {
        padding-right: 20px;
        margin-top: -200px;
        border: none;

        color: white;
        text-align: right;
        letter-spacing: 5px;
        font-size: 1rem;

        background-color: #ab8972;

        z-index: 5;
        cursor: pointer;
      }

      .layer {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: 20px 14px 0 0;
        opacity: 0;
        writing-mode: vertical-rl;

        &__wrapper {
          width: 100%;
          height: 100%;
        }

        span {
          display: block;

          &:first-child {
            padding-right: 14px;
            font-size: 1.5rem;
            letter-spacing: 5px;
          }

          &:last-child {
            font-weight: bold;
            font-size: 2.5rem;
          }
        }

        &-displayed {
          opacity: 1 !important;
        }
      }
    }

    &__footer {
      grid-row: 2;
      grid-column: 1;

      position: relative;
      display: flex;
      justify-content: space-around;
      align-items: flex-end;

      .container {
        position: relative;
        width: 35%;
        height: 80%;

        .layer {
          transition-duration: 2s !important;
        }

        &:last-child {
          width: 30%;
          height: 80%;

          .layer {
            transition-delay: 0.1s;
          }
        }

        .layer {
          transform: translateY(200%);

          &-displayed {
            transform: translateY(0);
          }
        }
      }
    }

    .layer {
      position: absolute;
      top: 0;
      left: 0;
      bottom: 0;
      right: 0;
      width: 100%;
      height: 100%;

      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;

      overflow: hidden;
      z-index: 0;

      &-image-wrapper {
        width: 100%;
        height: 100%;

        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;

        transform: scale(1.1);
      }
    }

    .portrait {
      position: absolute;
      top: -40%;
      left: 40%;
      width: 10%;
      height: 100%;

      z-index: 4;

      .layer {
        height: 33%;
        top: 40%;
        transform: translateY(150%);
        opacity: 0;

        &-displayed {
          transform: translateY(0);
          opacity: 1;
        }

        &-displayed-exit {
          transform: translateY(-150%);
          opacity: 0;
        }
      }
    }

    .line {
      position: absolute;
      bottom: 60%;
      left: 50%;
      width: 35%;
      height: 5px;
      border-top: 1.5px solid white;
      z-index: 3;

      transform: rotate(50deg) translateX(20%);
    }
  }
}
</style>