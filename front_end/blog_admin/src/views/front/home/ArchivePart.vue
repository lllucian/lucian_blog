<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Archives</h3>
      <ul class="categories">
        <li><a href="#">September 2018 <span>(6)</span></a></li>
        <li><a href="#">August 2018 <span>(8)</span></a></li>
        <li><a href="#">July 2018 <span>(2)</span></a></li>
        <li><a href="#">June 2018 <span>(7)</span></a></li>
        <li><a href="#">May 2018 <span>(5)</span></a></li>
        <li><a href="#">April 2018 <span>(3)</span></a></li>
      </ul>
    </div>
  </Waypoint>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Waypoint} from "vue-waypoint";

export default defineComponent({
  components: {
    Waypoint
  }, setup() {
    const animateClass: any = ref([]);
    const onChange = async (waypointState: any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return {onChange, animateClass};
  }
})
</script>