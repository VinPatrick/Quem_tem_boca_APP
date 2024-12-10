import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qtb.R

class SimpleAdapter(
    private var items: List<String>,
    private val onClick: (String, Int) -> Unit
) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_text)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item

        // Definindo o clique no item e passando o item e seu índice
        holder.itemView.setOnClickListener {
            onClick(item, position)
        }
    }

    override fun getItemCount(): Int = items.size

    // Método para atualizar a lista
    fun atualizarLista(novaLista: List<String>) {
        items = novaLista
        notifyDataSetChanged()
    }
}
