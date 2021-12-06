<template>
  <Form :title="$route.meta.title"></Form>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import Form from "/@/views/admin/tags/common/Form.vue"

export default defineComponent ({
  components: {
    Form
  },
  setup(){

  }
})
</script>

<style scoped>

</style>
