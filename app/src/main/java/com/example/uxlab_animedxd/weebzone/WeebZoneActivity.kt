package com.example.uxlab_animedxd.weebzone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uxlab_animedxd.R
import com.example.uxlab_animedxd.databinding.ActivityWeebZoneBinding
import com.example.uxlab_animedxd.HostActivity
import com.example.uxlab_animedxd.weebzone.adapter.BestMangaAdapter
import com.example.uxlab_animedxd.weebzone.adapter.LatestMangaAdapter
import com.example.uxlab_animedxd.weebzone.model.Manga

class WeebZoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeebZoneBinding

    private val bestNew = listOf(
        Manga("Jujutsu Kaisen", "A boy swallows a cursed object and becomes a vessel of a powerful demon. He joins a sorcerer school to fight curses.", R.drawable.jujutsu),
        Manga("Attack on Titan", "Humanity battles giant man-eating titans in a desperate attempt to survive behind massive walls.", R.drawable.aot),
        Manga("Oshi no Ko", "A shocking reincarnation tale about the dark side of Japan’s entertainment industry and the quest for revenge.", R.drawable.oshinoko),
        Manga("The Summer Hikaru Died", "A small-town boy senses something is off when his best friend comes back… different.", R.drawable.hikaru),
    )

    private val latest = listOf(
        Manga("Chainsaw Man", "Denji, a devil hunter with a chainsaw demon inside him, fights brutal devils in a world where fear manifests as monsters.", R.drawable.chainsaw),
        Manga("Spy x Family", "A spy must build a fake family to complete his mission—only to discover his “wife” is an assassin and “daughter” a telepath.", R.drawable.spy),
        Manga("My Hero Academia", "In a world full of superpowers (quirks), a powerless boy enters the top hero school to become the Symbol of Peace.", R.drawable.mha),
        Manga("Blue Lock", "After Japan’s loss in the World Cup, a radical football program aims to create the ultimate egotist striker.", R.drawable.bluelock),
        Manga("Tokyo Revengers", "A man travels back in time to high school to stop the death of his girlfriend and change the future.", R.drawable.tokyo_rev),
        Manga("Death Note", "A high school student gains a notebook that can kill anyone whose name is written in it—sparking a global manhunt.", R.drawable.deathnote)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeebZoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // greeting user
        val user = getSharedPreferences("user_prefs", MODE_PRIVATE)
            .getString("username", "User")
        binding.tvGreeting.text = "Welcome, $user!"

        // setup RecyclerViews
        binding.rvBestManga.apply {
            layoutManager = LinearLayoutManager(
                this@WeebZoneActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = BestMangaAdapter(bestNew)
        }

        binding.rvLatestManga.apply {
            layoutManager = GridLayoutManager(this@WeebZoneActivity, 2)
            adapter = LatestMangaAdapter(latest)
        }

        // toggle group logic
        val toggleGroup = binding.toggleGroup

        toggleGroup.check(R.id.btnMangas) // default pilih btnMangas

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener

            when (checkedId) {
                R.id.btnNews -> {
                    val intent = Intent(this, HostActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .putExtra("open_home", true)
                    startActivity(intent)
                    overridePendingTransition(0, 0) // ⬅️ Hapus transisi saat pindah ke News
                    finish()
                }

                R.id.btnMangas -> {
                    // Sudah di halaman ini, jangan lakukan apa-apa
                    // Optional: hapus efek visual jika perlu
                    // binding.btnMangas.clearAnimation()
                }
            }
        }

    }
}
