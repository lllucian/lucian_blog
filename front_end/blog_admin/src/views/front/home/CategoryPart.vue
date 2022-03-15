<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Destination</h3>
      <ul class="categories">
        <li><a href="#">Africa <span>(6)</span></a></li>
        <li><a href="#">Asia <span>(8)</span></a></li>
        <li><a href="#">Australia <span>(2)</span></a></li>
        <li><a href="#">Europe <span>(2)</span></a></li>
        <li><a href="#">North America <span>(7)</span></a></li>
        <li><a href="#">South America <span>(5)</span></a></li>
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
  },
  setup() {
    const animateClass:any = ref([]);
    const onChange = async (waypointState:any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return { onChange, animateClass };
  }
})
</script>