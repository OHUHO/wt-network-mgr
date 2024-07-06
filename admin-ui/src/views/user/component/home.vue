<template>
  <div class="row">
    <div class="col-12">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">公告列表</h4>
          <div class="row mt-4">
            <div class="col-sm-12 col-md-6">
              <div id="tickets-table_length" class="dataTables_length">
                <label class="d-inline-flex align-items-center">
                  展示&nbsp;
                  <b-form-select
                      v-model="perPage"
                      size="sm"
                      :options="pageOptions"
                  ></b-form-select
                  >&nbsp;条数
                </label>
              </div>
            </div>
            <!-- Search -->
            <div class="col-sm-12 col-md-6">
              <div
                  id="tickets-table_filter"
                  class="dataTables_filter text-md-right"
              >
                <label class="d-inline-flex align-items-center">
                  收索:
                  <b-form-input
                      v-model="filter"
                      type="search"
                      placeholder="Search..."
                      class="form-control form-control-sm ml-2"
                  ></b-form-input>
                </label>
              </div>
            </div>
            <!-- End search -->
          </div>
          <!-- Table -->
          <div class="table-responsive mb-0">
            <b-table
                :items="tableData"
                :fields="fields"
                responsive="sm"
                :per-page="perPage"
                :current-page="currentPage"
                :sort-by.sync="sortBy"
                :sort-desc.sync="sortDesc"
                :filter="filter"
                :filter-included-fields="filterOn"
                @filtered="onFiltered"
            ></b-table>
          </div>
          <div class="row">
            <div class="col">
              <div
                  class="dataTables_paginate paging_simple_numbers float-right"
              >
                <ul class="pagination pagination-rounded mb-0">
                  <!-- pagination -->
                  <b-pagination
                      v-model="currentPage"
                      :total-rows="rows"
                      :per-page="perPage"
                  ></b-pagination>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { tableData } from "./dataAdvancedtable";

 export default {
   name : "home",
   data() {
     return {
       tableData: tableData,
       title: "Advanced Table",
       items: [
         {
           text: "Tables",
           href: "/",
         },
         {
           text: "Advanced",
           active: true,
         },
       ],
       totalRows: 1,
       currentPage: 1,
       perPage: 10,
       pageOptions: [10, 25, 50, 100],
       filter: null,
       filterOn: [],
       sortBy: "age",
       sortDesc: false,
       fields: [
         { key: "公告标题", sortable: true },
         { key: "发布部门", sortable: true },
       /*  { key: "office", sortable: true },
         { key: "age", sortable: true },*/
         { key: "发布时间", sortable: true },
         // { key: "salary", sortable: true },
       ],
     };
   },
   computed: {
     /**
      * Total no. of records
      */
     rows() {
       return this.tableData.length;
     },
   },
   mounted() {
     // Set the initial number of items
     this.totalRows = this.items.length;
   },
   methods: {
     /**
      * Search the table data with search input
      */
     onFiltered(filteredItems) {
       // Trigger pagination to update the number of buttons/pages due to filtering
       this.totalRows = filteredItems.length;
       this.currentPage = 1;
     },
   },
 }
</script>