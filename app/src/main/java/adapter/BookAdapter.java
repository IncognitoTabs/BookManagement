package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hoangminhtai.bookmanagement.R;

import java.util.List;

import models.Book;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Book getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_layout, null);
            viewHolder.txtName = view.findViewById(R.id.txtName);
            viewHolder.txtID = view.findViewById(R.id.txtID);
            viewHolder.txtPrice = view.findViewById(R.id.txtPrice);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Book book = bookList.get(i);
        viewHolder.txtID.setText("Mã sách: " + book.getId());
        viewHolder.txtName.setText("Tên sách: " + book.getName());
        viewHolder.txtPrice.setText("Giá (VNĐ): " + book.getPrice().toString());

        return view;
    }
    private  class ViewHolder{
        TextView txtID,txtName,txtPrice;
    }
}
