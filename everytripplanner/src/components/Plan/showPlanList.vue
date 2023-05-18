<template>
    <div id="asdasd">
    <b-button v-b-modal.modal-1>달력 정하기</b-button>
        <b-modal id="modal-1" title="BootstrapVue">
            <label for="example-datepicker">시작 날짜</label>
            <b-form-datepicker id="start_date" v-model="startDate" class="mb-2"></b-form-datepicker>
            <label for="example-datepicker">끝날짜</label>
            <b-form-datepicker id="end_dater" v-model="endDate" class="mb-2"></b-form-datepicker>
        </b-modal>
        <div>{{ myDate }} DAY</div>
        <AttractionAdd></AttractionAdd>
    </div>
</template>

<script>
import AttractionAdd from "@/components/Plan/items/AttractionAdd.vue"
export default {

    components: {
        AttractionAdd
    },
    data() {
        return {
            message: '',
            startDate: "",
            endDate:"",
        };
    },
    created() {},
    methods: {
    },
    computed: {
        myDate: function () {
            const oldDate = new Date(this.startDate);
            const newDate = new Date(this.endDate);
            let diff = Math.abs(newDate.getTime() - oldDate.getTime());
            diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
            return diff;
        }
    }
};
</script>

<style scoped></style>