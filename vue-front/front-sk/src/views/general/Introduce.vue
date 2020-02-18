<template>
  <div id="HomePage">
    <v-app light id="hp_video">
      <v-content>
        <section class="introduce">
          <section class="hero">
            <div class="hero__main">
              <div class="layer layer-displayed" data-scene="1">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1513792859704-f49baf5c0b70?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=522c9283a0ad870843048349acb08736&auto=format&fit=crop&w=1050&q=80')"
                ></div>
              </div>
              <div class="layer" data-scene="2">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1510855412585-af057ac84251?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bc9e3d133bcd845118e779ea56c9bb49&auto=format&fit=crop&w=1189&q=80')"
                ></div>
              </div>
              <div class="layer" data-scene="3">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1517453881932-462e65217f7a?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=89c50aab146008513d49a905cfd903d4&auto=format&fit=crop&w=1050&q=80')"
                ></div>
              </div>
              <div class="text">
                <div class="text__layers">
                  <span class="layer layer-displayed" data-scene="1">Marcus Ohslon</span>
                  <span class="layer" data-scene="2">Benjamin Vunk</span>
                  <span class="layer" data-scene="3">Marisa Green</span>
                </div>
              </div>
            </div>
            <div class="hero__second">
              <div class="layer layer-displayed" data-scene="1">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1523944339743-0fe06f079939?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=32ea302b4d051f92ebd80985d90cb4d4&auto=format&fit=crop&w=658&q=80')"
                ></div>
              </div>
              <div class="layer" data-scene="2">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1512646737873-990ddc1a8e48?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1a92535fa6c18e78089680b09e039995&auto=format&fit=crop&w=650&q=80')"
                ></div>
              </div>
              <div class="layer" data-scene="3">
                <div
                  class="layer-image-wrapper"
                  style="background-image: url('https://images.unsplash.com/photo-1508901459911-fc63f8c7e521?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=ce36b4cfc6cbab935c17c5114a91f229&auto=format&fit=crop&w=334&q=80')"
                ></div>
              </div>
            </div>
            <div class="hero__third">
              <div class="layer__wrapper">
                <div class="layer layer-displayed" data-scene="1">
                  <span>Richard Steffon</span>
                  <span>S/ Moda</span>
                </div>
                <div class="layer" data-scene="2">
                  <span>Maria Jeferaf</span>
                  <span>Muse Magazine</span>
                </div>
                <div class="layer" data-scene="3">
                  <span>Zaileene</span>
                  <span>Fashion Floor</span>
                </div>
              </div>
              <button @click="switchLayer()">NEXT TALENT ></button>
              <!-- <button onclick="switchLayer(); return true;">NEXT TALENT ></button> -->
            </div>
            <div class="hero__footer">
              <div class="container">
                <div class="layer layer-displayed" data-scene="1">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1506091618538-5c362f734eab?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b6e37e3cf92364310d45e3d7940c4190&auto=format&fit=crop&w=1052&q=80')"
                  ></div>
                </div>
                <div class="layer" data-scene="2">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1500917129638-ca7e13bc30f3?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0e004a8ee3c1a65041db94f60439b704&auto=format&fit=crop&w=1050&q=80')"
                  ></div>
                </div>
                <div class="layer" data-scene="3">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1498387727476-b30055b2ef21?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=753c6cf02995c1bfc3f015037549b06b&auto=format&fit=crop&w=1050&q=80')"
                  ></div>
                </div>
              </div>
              <div class="container">
                <div class="layer layer-displayed" data-scene="1">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1492707892479-7bc8d5a4ee93?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7834d8f490640ffe7e7f251227679b3d&auto=format&fit=crop&w=701&q=80')"
                  ></div>
                </div>
                <div class="layer" data-scene="2">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1522322659370-3c98da30c99c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=38cc8e2b8e8c4439192c1130a861e3da&auto=format&fit=crop&w=655&q=80')"
                  ></div>
                </div>
                <div class="layer" data-scene="3">
                  <div
                    class="layer-image-wrapper"
                    style="background-image: url('https://images.unsplash.com/photo-1509631179647-0177331693ae?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=e1297fa068f9bd048e8e42b6f1ccbd1e&auto=format&fit=crop&w=334&q=80')"
                  ></div>
                </div>
              </div>
            </div>
            <div class="portrait">
              <div
                class="layer layer-displayed"
                data-scene="1"
                style="background-image: url('https://images.unsplash.com/photo-1505840717430-882ce147ef2d?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=72b653203146f33bb76721c00cf5cbb2&auto=format&fit=crop&w=334&q=80')"
              ></div>
              <div
                class="layer"
                data-scene="2"
                style="background-image: url('https://images.unsplash.com/photo-1512146587460-f94274cc6b9f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7ef91c21318c8ffa1a9da0ba9f5b9207&auto=format&fit=crop&w=334&q=80')"
              ></div>
              <div
                class="layer"
                data-scene="3"
                style="background-image: url('https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7880e3b80dd9960e65d543cd39d3540e&auto=format&fit=crop&w=334&q=80')"
              ></div>
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
        (this.sliderIndex + step) % 3 === 0 ? 3 : (this.sliderIndex + step) % 3;
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
  width: 100vw;
  height: 100vh;

  margin: 0;
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
      top: -50%;
      left: 47%;
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