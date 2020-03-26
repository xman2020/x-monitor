<template>
  <a-modal :title="title" :width="width" :visible="visible" @ok="confirm" @cancel="cancel">
    <a-form :form="form">
      <a-form-item label="IP" :labelCol="labelCol" :wrapperCol="wrapperCol">
        <a-input
          v-decorator="['ip', { rules: [{ required: true, message: '请输入IP' }] }]"
          placeholder="请输入IP"
        />
      </a-form-item>
      <a-form-item label="端口" :labelCol="labelCol" :wrapperCol="wrapperCol">
        <a-input
          v-decorator="['port', { rules: [{ required: true, message: '请输入端口' }] }]"
          placeholder="请输入端口"
        />
      </a-form-item>
      <a-form-item label="路径" :labelCol="labelCol" :wrapperCol="wrapperCol">
        <a-input
          v-decorator="['path']"
          placeholder="请输入路径"
        />
      </a-form-item>
      <a-form-item label="权重" :labelCol="labelCol" :wrapperCol="wrapperCol">
        <a-input
          v-decorator="['weight']"
          placeholder="请输入权重"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
export default {
  name: 'NodeForm',
  components: {},
  props: {},
  data () {
    return {
      title: '新增节点',
      width: 600,
      visible: false,
      form: this.$form.createForm(this),
      labelCol: { lg: { span: 6 }, sm: { span: 6 } },
      wrapperCol: { lg: { span: 14 }, sm: { span: 14 } }
    }
  },
  created () {},
  methods: {
    add () {
      this.title = '新增节点'
      // this.form.resetFields()
      this.open()
    },
    modify (node) {
      this.title = '修改节点'
      // this.form.resetFields()
      this.$nextTick(() => {
        this.form.setFieldsValue({ ...node })
      })
      this.open()
    },
    confirm () {
      console.log('confirm()')
      this.form.validateFields((err, values) => {
        console.log('validateErr:', err)
        console.log('formItems:', values)

        if (!err) {
          var node = Object.assign({}, values)
          console.log('node:', node)
          this.$emit('confirm', node)
          this.close()
        }
      })
    },
    cancel () {
      this.close()
    },
    open () {
      this.visible = true
    },
    close () {
      this.form.resetFields()
      this.visible = false
    }
  }
}
</script>
