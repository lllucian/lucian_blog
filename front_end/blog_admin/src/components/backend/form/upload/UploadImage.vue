<template>
  <div class="upload-container">
    <el-upload
        :multiple="false"
        :show-file-list="false"
        :on-success="handleImageSuccess"
        class="image-uploader"
        drag
        accept="image/*"
        action="api/admin/upload_file/upload"
        :data="{bucketName: 'pic'}"
        name="file"
        :headers="{'Authorization': this.$store.getters.getToken}"
    >
      <Icon icon="bxs:cloud-upload" :size="60" :style="{display: 'inline-block', margin: '40px 0 16px'}" />
      <div class="el-upload__text">
        将文件拖到此处，或<em>点击上传</em>
      </div>
    </el-upload>
    <div class="image-preview image-app-preview">
      <div
          v-show="imageUrl.length>1"
          class="image-preview-wrapper"
      >
        <img :src="imageUrl">
        <div class="image-preview-action">
          <Icon icon="ep:delete" :size="60" @clickMethod="rmImage"/>
        </div>
      </div>
    </div>
    <div class="image-preview">
      <div
          v-show="imageUrl.length>1"
          class="image-preview-wrapper"
      >
        <img :src="imageUrl">
        <div class="image-preview-action">
          <Icon icon="ep:delete" :size="60" @clickMethod="rmImage"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref, toRefs, watch} from "vue";
import Icon from "/@/components/common/basic/Icon.vue";
import {getRequest} from "/@/requests";

export default defineComponent({
  props: {
    modelValue: {type: [String, Number]},
    imageUrl: {type: String}
  },
  components: {
    Icon
  },
  setup(props, context) {
    const {modelValue, imageUrl} = toRefs(props);
    const emitInput = (value: any, image: any) => {
      context.emit('update:modelValue', value);
      context.emit('update:imageUrl', image);
    }

    watch(modelValue, (newValue, oldValue) => {
      if (!newValue){
        imageUrl.value = '';
      }
    })
    const handleImageSuccess = (res: any) => {
      emitInput(res.data.id, res.data.fileUrl);
    }

    const rmImage = () => {
      emitInput('', '');
    }

    return {handleImageSuccess, imageUrl, rmImage}
  }
});

</script>

<style lang="scss" scoped>
.upload-container {
  width: 100%;
  position: relative;
  &:after {
    content: "";
    display: table;
    clear: both;
  }

  .image-uploader {
    width: 35%;
    float: left;
  }

  .image-preview {
    width: 200px;
    height: 200px;
    position: relative;
    border: 1px dashed #d9d9d9;
    float: left;
    margin-left: 50px;

    .image-preview-wrapper {
      position: relative;
      width: 100%;
      height: 100%;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .image-preview-action {
      position: absolute;
      width: 100%;
      height: 100%;
      left: 0;
      top: 0;
      cursor: default;
      text-align: center;
      color: #fff;
      opacity: 0;
      font-size: 20px;
      background-color: rgba(0, 0, 0, .5);
      transition: opacity .3s;
      cursor: pointer;
      text-align: center;
      line-height: 200px;

      .el-icon-delete {
        font-size: 36px;
      }
    }

    &:hover {
      .image-preview-action {
        opacity: 1;
      }
    }
  }

  .image-app-preview {
    width: 320px;
    height: 180px;
    position: relative;
    border: 1px dashed #d9d9d9;
    float: left;
    margin-left: 50px;

    .app-fake-conver {
      height: 44px;
      position: absolute;
      width: 100%; // background: rgba(0, 0, 0, .1);
      text-align: center;
      line-height: 64px;
      color: #fff;
    }
  }
}
</style>