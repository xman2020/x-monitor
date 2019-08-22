<template>
  <div id="metricAdd">
    <a-card :body-style="{ padding: '24px 32px' }" :bordered="false">
      <a-form @submit="handleSubmit" :form="form">
        <a-form-item label="指标ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['id', { rules: [{ required: true, message: '请输入指标ID' }] }]"
            name="id"
            placeholder="请输入指标ID"
          />
        </a-form-item>
        <a-form-item label="流水号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['serialNo', { rules: [{ required: true, message: '请输入流水号' }] }]"
            name="serialNo"
            placeholder=""
          />
        </a-form-item>
        <a-form-item label="目标" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['target', { rules: [{ required: true, message: '请输入目标' }] }]"
            name="target"
            placeholder=""
          />
        </a-form-item>
        <a-form-item label="指标名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['name', { rules: [{ required: true, message: '请输入指标名' }] }]"
            name="name"
            placeholder=""
          />
        </a-form-item>
        <a-form-item label="指标值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['value', { rules: [{ required: true, message: '请输入指标值' }] }]"
            name="value"
            placeholder=""
          />
        </a-form-item>
        <a-form-item label="值类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select name="valueType" v-decorator="['valueType', {initialValue: '02'}]">
            <a-select-option value="01">数值</a-select-option>
            <a-select-option value="02">字符串</a-select-option>
            <a-select-option value="03">日期时间</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="采集时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-date-picker name="collectTime" v-decorator="['collectTime']" showTime format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="记录时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            v-decorator="['recordTime', { rules: [{ required: true, message: '请输入记录时间' }] }]"
            name="recordTime"
            placeholder="请输入记录时间"
          />
        </a-form-item>
        <a-form-item :wrapperCol="{ span: 24 }" style="text-align: center">
          <a-button htmlType="submit" type="primary">提交</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script>
import metricApi from '@/api/metricApi'

export default {
  name: 'MetricAdd',
  data () {
    return {
      labelCol: { lg: { span: 7 }, sm: { span: 7 } },
      wrapperCol: { lg: { span: 10 }, sm: { span: 17 } },
      form: this.$form.createForm(this)
    }
  },
  mounted () {
    // console.log(this.form)
  },
  methods: {
    handleSubmit (e) {
      e.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          // eslint-disable-next-line no-console
          console.log('Received values of form: ', values)
          metricApi.metricAdd(values).then(res => {
            console.log('metricAdd.call()', res)
          })
        }
      })
    }
  }
}
</script>
