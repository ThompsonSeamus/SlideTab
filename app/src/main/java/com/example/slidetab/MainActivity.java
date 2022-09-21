package com.example.slidetab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String[] HOBBITS = {"Bilbo", "Frodo", "Samwise", "Golem"};

    ViewPager2 pager;
    TabLayout tabLayout;
    List<String> hobbits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHobbits();

        pager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        pager.setAdapter(new SlideAdapter(this));
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(hobbits.get(position));
            }
        }).attach();

    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_item_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getHobbits(){
        hobbits = new ArrayList<>(Arrays.asList(HOBBITS));

    }

    
    private class SlideAdapter extends FragmentStateAdapter {
        public SlideAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if(position == 0){
                return new bilboFragment();
            }else if(position == 1){
                return new frodoFragment();
            }else if (position == 2){
                return new samwiseFragment();
            }else{
                return new golemFragment();
            }
        }

        @Override
        public int getItemCount() {
            return hobbits.size();
        }
    }{

    }
}