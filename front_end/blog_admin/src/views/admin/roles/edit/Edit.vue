<template>
  <Form :title="$route.meta.title" :roleId="$route.params.id"></Form>
</template>

<script lang="ts" setup>
import Form from "../common/Form.vue"
</script>
