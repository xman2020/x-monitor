// 只能新增提供者
<template>
  <a-form :form="form">
    <a-card title="基本信息" style="margin-bottom: 8px">
      <a-row>
        <a-col :span="12">
          <a-form-item label="提供者ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input
              v-decorator="['id', { rules: [{ required: true, message: '请输入提供者ID' }] }]"
              placeholder="请输入提供者ID"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="提供者名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input
              v-decorator="['name', { rules: [{ required: true, message: '请输入提供者名称' }] }]"
              placeholder="请输入提供者名称"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input
              v-decorator="['desc', { rules: [{ required: true, message: '请输入描述' }] }]"
              placeholder="请输入描述"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-select v-decorator="['status', { initialValue: 'DISABLE' }]">
              <a-select-option value="DISABLE">停用</a-select-option>
              <a-select-option value="ENABLE">启用</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-select v-decorator="['type', { initialValue: 'HTTP' }]">
              <a-select-option value="HTTP">HTTP</a-select-option>
              <a-select-option value="LOCAL">本地</a-select-option>
              <a-select-option value="KAFKA">KAFKA</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="路径" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-decorator="['path']" placeholder="请输入路径" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-item label="负载均衡" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-select v-decorator="['loadBalance', { initialValue: 'RANDOM' }]">
              <a-select-option value="RANDOM">随机</a-select-option>
              <a-select-option value="ROUND">轮询</a-select-option>
              <a-select-option value="WEIGHT">权重</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
    </a-card>
    <a-card title="节点信息">
      <a-table :columns="nodeColumns" :dataSource="nodes" :pagination="false">
        <template slot="ip" slot-scope="text, record">
          <a-input
            v-model="record.ip"
            v-decorator="['ip', { rules: [{ required: true, message: '请输入节点IP' }] }]"
            placeholder="请输入节点IP"
          />
        </template>
        <template slot="port" slot-scope="text, record">
          <a-input
            v-model="record.port"
            v-decorator="['port', { rules: [{ required: true, message: '请输入节点端口' }] }]"
            placeholder="请输入节点端口"
          />
        </template>
        <template slot="path" slot-scope="text, record">
          <a-input
            v-model="record.path"
            v-decorator="['path', { rules: [{ required: true, message: '请输入路径' }] }]"
            placeholder="请输入路径"
          />
        </template>
        <template slot="weight" slot-scope="text, record">
          <a-input
            v-model="record.weight"
            v-decorator="['weight', { rules: [{ required: true, message: '请输入权重' }] }]"
            placeholder="请输入权重"
          />
        </template>
        <template slot="operation" slot-scope="text, record">
          <a-popconfirm title="是否要删除此节点？" @confirm="deleteNode(record)">
            <a>删除</a>
          </a-popconfirm>
        </template>
      </a-table>
      <a-button style="width: 100%; margin-top: 16px; margin-bottom: 8px" type="dashed" icon="plus" @click="addNode">新增节点</a-button>
    </a-card>
    <footer-tool-bar>
      <a-button type="primary" @click="saveProvider" :loading="false">保存</a-button>
    </footer-tool-bar>
  </a-form>
</template>

<script>
import providerApi from '@/api/providerApi'
import FooterToolBar from '@/components/FooterToolbar'

export default {
  name: 'ProviderForm',
  components: {
    FooterToolBar
  },
  data () {
    return {
      labelCol: { lg: { span: 7 }, sm: { span: 7 } },
      wrapperCol: { lg: { span: 10 }, sm: { span: 17 } },
      form: this.$form.createForm(this),
      nodeColumns: [
        {
          title: 'IP',
          dataIndex: 'ip',
          key: 'ip',
          width: '25%',
          scopedSlots: { customRender: 'ip' }
        },
        {
          title: '端口',
          dataIndex: 'port',
          key: 'port',
          width: '20%',
          scopedSlots: { customRender: 'port' }
        },
        {
          title: '路径',
          dataIndex: 'path',
          key: 'path',
          width: '25%',
          scopedSlots: { customRender: 'path' }
        },
        {
          title: '权重',
          dataIndex: 'weight',
          key: 'weight',
          width: '20%',
          scopedSlots: { customRender: 'weight' }
        },
        {
          title: '操作',
          key: 'operation',
          scopedSlots: { customRender: 'operation' }
        }
      ],
      nodes: [
        {
          ip: '172.22.0.1',
          port: 8080
        }
      ]
    }
  },
  mounted () {
    console.log(this.provider)
    console.log(this.nodes)
    // this.provider.nodes.push({})
  },
  methods: {
    addNode () {
      this.nodes.push({})
    },
    deleteNode (record) {
      console.log(this.nodes)
      console.log(record)
      this.nodes = this.nodes.filter(item => item.ip !== record.ip)
    },
    saveProvider () {
      this.form.validateFields((err, values) => {
        console.log(err)
        console.log(values)
        values.nodes = this.nodes
        console.log(values)

        if (!err) {
          providerApi.saveProvider(values).then(res => {
            if (res.success) {
              this.$message.success('提供者保存成功！')
            } else {
              this.$message.error('提供者保存失败！ ')
            }
          })
        }
      })
    }
  }
}
</script>
