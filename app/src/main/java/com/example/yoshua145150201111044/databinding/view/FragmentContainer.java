package com.example.yoshua145150201111044.databinding.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.databinding.ActivityContainerBinding;
import com.example.yoshua145150201111044.databinding.viewmodel.FragmentContainerViewModel;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


/**
 * Created by iboy on 05/07/17.
 Alur aplikasi: FragmentContainer -> FragmentContainerViewModel -> ClassView -> ClassViewModel -> ClassAdapter(jika ada)
 */


public class FragmentContainer extends AppCompatActivity implements Drawer.OnDrawerItemClickListener{
    public static final int CARD_VIEW=0;
    public static final int SWIPE_VIEW=1;
    public static final int HOME_VIEW=2;
    public static final int WISATA_VIEW=3;
    public static final int MAP_VIEW=4;
    public static final int ABOUT_VIEW=5;


    public static final int HOME_IDENTIFIER=1;
    public static final int WISATA_IDENTIFIER =2;
    public static final int GALLERY_IDENTIFIER=3;
    public static final int MAP_IDENTIFIER=4;
    public static final int ABOUT_IDENTIFIER=5;

    //snackbar adalah pesan kotak hitam yang muncul dibawah
    public static Snackbar snackbar;

    public static ActivityContainerBinding activityContainerBinding;
    public static Drawer result;

    @Override //pada class extends Activity
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //menampilkan layout
        activityContainerBinding= DataBindingUtil.setContentView(this, R.layout.activity_container);

        //mengambil toolbar dari binding layout
        Toolbar toolbar = (Toolbar) findViewById(activityContainerBinding.myAwesomeToolbar.getId());
        toolbar.setTitle("Awal");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //digunakan kalau tidak menggunakan library drawer mikepenz:materialdrawer
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, activityContainerBinding.drawerLayout, toolbar, R.string.muted_dark, R.string.muted_light);
//        activityContainerBinding.drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();//untuk tombol tiga garis di kiri atas

        //Pembuatan item menu pada materialdrawer
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(HOME_IDENTIFIER)
                .withName(R.string.drawer_item_home)
//                .withSelectable(false)
                .withIcon(GoogleMaterial.Icon.gmd_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(WISATA_IDENTIFIER)
                .withName(R.string.drawer_item_wisata)
                .withIcon(FontAwesome.Icon.faw_plane);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(GALLERY_IDENTIFIER)
                .withName(R.string.drawer_item_gallery)
                .withIcon(FontAwesome.Icon.faw_picture_o);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(MAP_IDENTIFIER)
                .withName(R.string.drawer_item_map)
                .withIcon(FontAwesome.Icon.faw_map);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(ABOUT_IDENTIFIER)
                .withName(R.string.drawer_item_about)
                .withIcon(FontAwesome.Icon.faw_info_circle);
        //Membuat header untuk drawer
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
//                .addProfiles(
//                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
//                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //membuat drawer dengan DrawerBuilder
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item3,
                        item4,
                        item5)
                .build();
        //mencoba mengubah item pada drawer, withBadge seberapa banyak notifnya, badge adalah sejenis bidang notif angka
//        item1.withName("Home").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify drawer telah mengupdate item1
        result.updateItem(item1);
        //set apa yang dilakukan item yang diklik
        result.setOnDrawerItemClickListener(this);

        //membuat VM fragmentContainer
        FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,HOME_VIEW);
        //mengeset data binding pada layout card_peserta
        activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);


    }



    //override untuk method pada Drawer.OnDrawerItemClickListener saat item pada drawer di click
    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        if(drawerItem.getIdentifier()==HOME_IDENTIFIER){
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,HOME_VIEW);
            //mengeset data binding pada layout card_peserta
            activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            result.closeDrawer();
        }
        else if(drawerItem.getIdentifier()==GALLERY_IDENTIFIER){
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,CARD_VIEW);
            //mengeset data binding pada layout card_peserta
            activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            result.closeDrawer();
        }
        else if(drawerItem.getIdentifier()== WISATA_IDENTIFIER){
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,WISATA_VIEW);
            //mengeset data binding pada layout card_peserta
            activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            result.closeDrawer();
        }
        else if(drawerItem.getIdentifier()== MAP_IDENTIFIER){
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,MAP_VIEW);
            //mengeset data binding pada layout card_peserta
            activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            result.closeDrawer();
        }
        else if(drawerItem.getIdentifier()== ABOUT_IDENTIFIER){
            //membuat VM fragmentContainer
            FragmentContainerViewModel fragmentContainerViewModel=new FragmentContainerViewModel(this,activityContainerBinding,ABOUT_VIEW);
            //mengeset data binding pada layout card_peserta
            activityContainerBinding.setFragmentContainerViewModel(fragmentContainerViewModel);
            result.closeDrawer();
        }
        return true;
    }
    @Override
    public void onPause() {
        super.onPause();
        //mematikan GPS setelah selesai pakai
//        String provider= Settings.Secure.getString(getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//        if(provider.contains("gps")){
//            final Intent poke=new Intent();
//            poke.setClassName("com.android.settings","com.android.settings.widget.SettingsAppWidgetProvider");
//            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            poke.setData(Uri.parse("3"));
//            sendBroadcast(poke);
//        }
    }
}
