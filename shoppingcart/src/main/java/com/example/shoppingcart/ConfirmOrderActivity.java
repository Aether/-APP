package com.example.shoppingcart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import cn.bmob.v3.Bmob;


public class ConfirmOrderActivity extends AppCompatActivity {

    private RecyclerView rvSelected;
    private SelectAdapter1 selectAdapter;
    private TextView tvSumCost;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        Bmob.initialize(this, "c12ad56b69a6e30cb8cc89b566379d19");
        rvSelected = (RecyclerView) findViewById(R.id.selectRecyclerView123);
        selectAdapter = new SelectAdapter1(ConfirmOrderActivity.this,ShoppingCartActivity.selectedList);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        rvSelected.setAdapter(selectAdapter);

        Intent intent =getIntent();
        double sumCost = intent.getDoubleExtra("sumCost",0);
        tvSumCost = (TextView) findViewById(R.id.tvSumCost);
        tvSumCost.setText("$"+ sumCost);

        btnConfirm = (Button) findViewById(R.id.btnConfirmSubmit);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(ConfirmOrderActivity.this).create();
                alert.setTitle("确定提交订单？");
                alert.setMessage("请仔细检查收货人信息是否正确填写");
                //添加取消按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"返回修改",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //添加"确定"按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE,"确认提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ConfirmOrderActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        });
    }

}

class SelectAdapter1 extends RecyclerView.Adapter<SelectAdapter1.ViewHolder> {

    private SparseArray<Goods> dataList;
    private NumberFormat nf;
    private LayoutInflater mInflater;

    public SelectAdapter1(ConfirmOrderActivity activity, SparseArray<Goods> dataList) {
        this.dataList = dataList;
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_confirm_goods,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goods item = dataList.valueAt(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if(dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Goods item;
        private TextView tvCost, tvCount, tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvCount = (TextView) itemView.findViewById(R.id.count);
        }

        public void bindData(Goods item){
            this.item = item;
            tvName.setText(item.name);
            tvCost.setText(nf.format(item.count * item.price));
        }
    }
}

