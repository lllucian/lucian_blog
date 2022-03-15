<template>
  <div class="row">
    <div class="col-md-12" v-for="i in 10" :key="i">
      {{animateClass}}
      <Waypoint @change="onChange">
        <div class="blog-entry ftco-animate" :class="animateClass">
          <a href="#" class="img" style="background-image: url('/src/assets/image_1.jpg');"></a>
          <div class="text pt-2 mt-5">
            <span class="big">South America</span>
            <h3 class="mb-4"><a href="#">Hawaii known as the Big Island</a></h3>
            <p class="mb-4">Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
            <div class="author mb-4 d-flex align-items-center">
              <a href="#" class="img" style="background-image: url('/src/assets/image_1.jpg');"></a>
              <div class="ml-3 info">
                <span>Written by</span>
                <h3><a href="#">Dave Lewis</a>, <span>October 04, 2018</span></h3>
              </div>
            </div>
            <div class="meta-wrap d-md-flex align-items-center">
              <div class="half order-md-last text-md-right">
                <p class="meta">
                  <span><i class="icon-heart"></i>3</span>
                  <span><i class="icon-eye"></i>100</span>
                  <span><i class="icon-comment"></i>5</span>
                </p>
              </div>
              <div class="half">
                <p><a href="#" class="btn btn-primary p-3 px-xl-4 py-xl-3">Continue Reading</a></p>
              </div>
            </div>
          </div>
        </div>
      </Waypoint>
    </div>
  </div>

</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import { Waypoint } from 'vue-waypoint'

export default defineComponent({
  components: {
    Waypoint
  },
  setup() {
    const animateClass:any = ref([]);
    const onChange = async (waypointState:any) => {
      // Going can be:
      // IN
      // OUT
      console.log(waypointState.going)
      console.log(waypointState.direction);
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return { onChange, animateClass };
  }
})
</script>