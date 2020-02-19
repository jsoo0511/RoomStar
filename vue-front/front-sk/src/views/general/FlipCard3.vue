<template>
<v-row id="m">
    <section>
        <article>
            <h2>노래 배틀하기</h2>
            <p>This is some description text for this panel.</p>
        </article>
    </section>
    <section>
        <article>
            <h2>노래 배틀하기</h2>
            <p>나는야 가수왕!</p>
            <!-- <router-link id="link_btn1" :to="'/MyPage2/'" tag=button>배틀하기</router-link> -->
            <button id="link_btn1" @click="$router.push('/gameRoom/')">배틀하기</button>
        </article>
    </section>
    <section>
        <article>
            <h2>배틀 시청하기</h2>
            <p>그치만.. 듣지 않으면 알 수 없는걸...</p>
            <!-- <button id="link_btn2">시청하기</button> -->
            <button id="link_btn2" @click="$router.push('/waitingRoom/')">시청하기</button>
        </article>
    </section>
    <section>
        <article>
            <h2>노래 자랑하기</h2>
            <p>5252.. 그 정도면 도내 상위랭크라구?</p>
            <!-- <button id="link_btn3">자랑하기</button> -->
            <button id="link_btn3" @click="$router.push('/SharingPage/')">자랑하기</button>
        </article>
    </section>
    <section>
        <article>
        </article>
    </section>
    </v-row>
</template>

<script>
import router from "@/routes";

export default {
  name: "flipcard3",
  components: {},
  data() {
    return {
        
    };
  },
   methods: {
  }
}

</script>
<style lang="scss" scoped>
$img-dir: "../../assets/images/a";

$section-amount: 5;
// Try messing with the rotate value
$section-rotate: 15;

$mq-desktop: "min-width: 630px";

* {
  box-sizing: border-box;
}
#m{
    background-color:rgba(0,0,0,0.9);
}

main {
  display: flex;
  flex-direction: column;
  width: 100%;
  transform: translate3d(0, 0, 0);
  @media ($mq-desktop) {
    flex-direction: row;
    width: 100% + ($section-rotate * 2);
    margin-left: percentage($section-rotate) * -.01;
  }
  section:not(:first-child):not(:last-child):hover {
    flex: 2;
    &:after {
      opacity: .8;
    }
    article {
      opacity: 1;
      transform: translateY(0);
      transition:
        opacity .2s .2s,
        transform .2s .2s;
      @media ($mq-desktop) {
        transform: 
          translateY(0)
          skewX(-#{$section-rotate}deg); 
      }
    }
  }
}

section {
  flex: 1;
  position: relative;
  width: 100%;
  min-height: 20vh;
  overflow: hidden;
  z-index: 1;
  transition: 
    flex-grow .2s,
    opacity .2s;
  &:before {
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
    background-position: center;
    background-size: cover;
    transition:
      transform .2s,
      width .2s;
  }
  &:after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: black;
    opacity: 0;
    transition: opacity .2s;
  }
  @media ($mq-desktop) {
    width: 20%;
    height: 100vh;
    margin-right: -1px;
    transform: 
      skewX(#{$section-rotate}deg)
      translateZ(0);
    &:before {
      left: -100%;
      width: 400%;
      transform: skewX(-#{$section-rotate}deg);
    }
  }
  @for $i from 1 through $section-amount {
    &:nth-child(#{$i}) {
      &:before {
        background-color: darken(red, $i * 5);
        background-image: url(#{$img-dir}#{$i}.jpg);
        .hide-images & {
          background-image: none;
        }
      }
    }
    &:first-child,
    &:last-child {
      &:before {
        background-color: darken(red, 30%);
      }
      &:after {
        opacity: .5;
      }
      article {
        display: none;
      }
    }
  }
}

article {
  position: relative;
  padding: 24px;
  width: 100%;
  height: 100%;
  text-align: center;
  color: white;
  z-index: 1;
  transition:
    opacity .2s,
    transform .2s;
  @media ($mq-desktop) {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: absolute;
    top: 0; right: 0; left: 0;
    margin: auto;
    opacity: 0;
    transform: 
      translateY(24px)
      skewX(-#{$section-rotate}deg); 
  }
}

h2 {
  font-size: 32px;
  margin-bottom: 12px;
}

button {
  font-family: inherit;
  font-weight: bold;
  color: var(--white-1);

  letter-spacing: 2px;

  padding: 9px 20px;
  border: 1px solid var(--grey);
  border-radius: 1000px;
  background: transparent;
  transition: .3s;

  cursor: pointer;
}

#link_btn1{
  background-color:rgba(207,156,1,0.8) !important;
}

#link_btn2{
  background-color:rgba(12,149,1,0.6) !important;
}

#link_btn3{
  background-color:rgba(1,171,207,0.6) !important;
}

button:hover,
button:focus {
  color: var(--primary);
  background: hsla(var(--hue), 25%, 10%, .2);
  border-color: currentColor;
}

button:active {
  transform: translate(2px);
}
</style>