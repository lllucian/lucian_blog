<template>
  <Waypoint @change="onChange">
    <div class="hero-wrap" style="background-image: url('src/assets/bg_1.jpg');"
         :style="{height: pageHeight, backgroundPosition: imagePosition}" v-scroll="handleScroll">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
             data-scrollax-parent="true" :style="{height: pageHeight}">
          <div class="col-md-9 text-center ftco-animate" :class="animateClass">
            <p class="breadcrumbs" ><span class="mr-2"><a
                href="index.html">Home</a></span> <span>Articles</span></p>
            <h1 class="mb-3 bread">Single Articles</h1>
          </div>
        </div>
      </div>
    </div>
  </Waypoint>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {Waypoint} from "vue-waypoint";

export default defineComponent({
  components: {
    Waypoint
  },
  directives: {
    scroll: {
      mounted(el, binding){
        let f = function (evt: any) {
          if (binding.value(evt, el)) {
            window.removeEventListener('scroll', f)
          }
        }
        window.addEventListener('scroll', f)
      }
    }
  },
  setup() {
    const pageHeight = ref(window.innerHeight + "px");
    const getPageRiseHeight = async () => {
      pageHeight.value = window.innerHeight + "px";
    };

    const animateClass:any = ref([]);
    const onChange = async (waypointState:any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    const imagePosition = ref( "50% 50%");

    const handleScroll = () => {
      const st = window.scrollY;
      imagePosition.value = "50% " + st + "px";
    }
    onMounted(() => {
      window.addEventListener("resize", getPageRiseHeight);
    });
    return {pageHeight, onChange, animateClass, imagePosition, handleScroll}
  }
})
</script>