<?php

class Smart_Ad_Tags_Settings {

    private $in_post_ad_settings_key = 'in_post_ad_settings';
    private $short_code_ad_settings_key = 'short_code_ad_settings';
    private $plugin_options_key = 'banner_ads';
    private $plugin_settings_tabs = array();

    function __construct() {

      add_action('admin_head', array( &$this, 'show_banner') );
      add_action('wp_ajax_show_banner_action', array( &$this, 'show_banner_now') );
      add_action('admin_head', array( &$this, 'shows_banner') );
      add_action('wp_ajax_shows_banner_action', array( &$this, 'shows_banner_now') );
      add_action( 'admin_init', array( &$this, 'register_in_post_ad_settings' ) );
      add_action( 'admin_init', array( &$this, 'register_short_code_ad_settings' ) );
      add_action('admin_menu', array( &$this, 'ad_create_menu' ));

    }

    function register_in_post_ad_settings() {
     $this->plugin_settings_tabs[$this->in_post_ad_settings_key] = 'Content AD';
    }

    function register_short_code_ad_settings() {
     $this->plugin_settings_tabs[$this->short_code_ad_settings_key] = 'Short Code';
    }

    function ad_create_menu() {
      add_menu_page('Smart Ad','Smart Ad','administrator','banner_ads', array( &$this, 'ad_setting_page'), plugins_url('smart-ad-tags/img/icon.png'), 3.3);
    }

    function shows_banner() {
      $protocol = isset( $_SERVER["HTTPS"] ) ? 'https://' : 'http://';
      $ajaxurl = admin_url( 'admin-ajax.php', $protocol );

      echo "<script type=\"text/javascript\">

       jQuery(document).ready(function() {
        jQuery('#listadgroup').change(function() {
         var listed = jQuery('#listadgroup').val();
         var data = {
          action: 'shows_banner_action',
          list: listed
         };

         jQuery.post('$ajaxurl', data, function(response) {
          jQuery('#results').html(response);
         });
        });
       });
      </script>";
    }

    function shows_banner_now() {

     global $wpdb;
     $table_namea = $wpdb->prefix . "bannerad";
     $gp = $_POST["list"];
     $yourad = $wpdb->get_results(
       "
        SELECT *
        FROM $table_namea
       "
     );

     foreach ($yourad as $ads) {

       $group = $ads->adgrp;

       if($group == $gp) {
          $ad = $ads->bannarad;
          $ad = stripslashes($ad);
          break;
       }
     }

     if($ad) {
      echo $ad;
     } else {
      echo 'This slot is empty.';
     }

     die();
    }

    function show_banner() {
      $protocol = isset( $_SERVER["HTTPS"] ) ? 'https://' : 'http://';
      $ajaxurl = admin_url( 'admin-ajax.php', $protocol );

      echo "<script type=\"text/javascript\">

       jQuery(document).ready(function() {
        jQuery('#listadsgroup, #timeshowing').change(function() {
         var listed = jQuery('#listadsgroup').val();
         var adstime = jQuery('#timeshowing').val();
         var data = {
          action: 'show_banner_action',
          list: listed,
          showtimer : adstime
         };

         jQuery.post('$ajaxurl', data, function(response) {
          jQuery('#result').html(response);
         });
        });
       });
      </script>";
    }

    function show_banner_now() {

     global $wpdb;
     $table_nameb = $wpdb->prefix . "bannerads";
     $gp = $_POST["list"];
     $swt = $_POST["showtimer"];
     $yourad = $wpdb->get_results(
       "
        SELECT *
        FROM $table_nameb
       "
     );

     foreach ($yourad as $ads) {

       $showt = $ads->showtimes;
       $group = $ads->adsgrp;

       if($showt == $swt && $group == $gp) {
          $ad = $ads->bannerad;
          $ad = stripslashes($ad);
          break;
       }
     }

     if($ad) {
      echo $ad;
     } else {
      echo 'This slot is empty.';
     }

     die();
    }

    function ad_setting_page() {
      $current_tab = isset( $_GET['tab'] ) ? $_GET['tab'] : $this->in_post_ad_settings_key;
      echo '<h2 class="smart-header-setting"></h2>';
      echo '<h2 class="nav-smart-tabs-wrapper">';
      foreach ( $this->plugin_settings_tabs as $tab_key => $tab_caption ) {
       $active = $current_tab == $tab_key ? 'nav-tab-active' : '';
       echo '<a class="nav-tab ' . $active . '" href="?page=' . $this->plugin_options_key . '&tab=' . $tab_key . '">' . $tab_caption . '</a>';
      }
      echo '</h2><hr class="smart"/>';

      if ( $_POST["bannerads"] && $_POST["group"] && $_POST["showtimes"] ) {
       global $wpdb;
       $table_nameb = $wpdb->prefix . "bannerads";
       $ad = $_POST["bannerads"];
       $gp = $_POST["group"];
       $swt = $_POST["showtimes"];
       $insert = true;

       $yourad = $wpdb->get_results(
        "
         SELECT *
         FROM $table_nameb
        "
       );

       foreach ($yourad as $ads) {

        $showt = $ads->showtimes;
        $group = $ads->adsgrp;

        if($showt == $swt && $group == $gp) {
          $rows_affected = $wpdb->update( $table_nameb, array( 'time' => current_time('mysql'), 'bannerad' => $ad  ), array('showtimes' => $swt, 'adsgrp' => $gp), array( '%d', '%s' ), array( '%s', '%s' ));
          $insert = false;
          break;
        }
       }

       if($insert) {
          $rows_affected = $wpdb->insert( $table_nameb, array( 'time' => current_time('mysql'), 'showtimes' => $swt, 'adsgrp' => $gp,   'bannerad' => $ad  ) );
       }
      } else if (isset($_POST["delsgroup"])) {
          global $wpdb;
          $table_nameb = $wpdb->prefix . "bannerads";
          $delgrps = $_POST["delsgroup"];
          $wpdb->query( " DELETE FROM $table_nameb WHERE adsgrp = '$delgrps' ");
      } else if($_POST["bannerad"] && $_POST["adgroup"] ) {
          global $wpdb;
          $table_namea = $wpdb->prefix . "bannerad";
          $ad = $_POST["bannerad"];
          $gp = $_POST["adgroup"];
          $insert = true;

          $yourad = $wpdb->get_results(
           "
            SELECT *
            FROM $table_namea
           "
          );

          foreach ($yourad as $add) {

           $group = $add->adgrp;

           if($group == $gp) {
            $rows_affected = $wpdb->update( $table_namea, array( 'time' => current_time('mysql'), 'bannarad' => $ad  ), array('adgrp' => $gp), array( '%d', '%s' ), array( '%s' ));
            $insert = false;
            break;
           }
          }

          if($insert) {
           $rows_affected = $wpdb->insert( $table_namea, array( 'time' => current_time('mysql'), 'adgrp' => $gp,   'bannarad' => $ad  ) );
          }
      } else if (isset($_POST["delgroup"])) {
          global $wpdb;
          $table_namea = $wpdb->prefix . "bannerad";
          $delgrp = $_POST["delgroup"];
          $wpdb->query( " DELETE FROM $table_namea WHERE adgrp = '$delgrp' ");
      }

      if( $current_tab == 'short_code_ad_settings' ) {
         global $wpdb;
         $table_nameb = $wpdb->prefix . "bannerads";
         $adsgroup = $wpdb->get_results(
           "
            SELECT * FROM $table_nameb
           "
          );

          $nonduplicated = array();
          $noduplicated = array();?>

          <div class="main_smart">
           <div class="left_smart">

            <h2><?php _e('Create or Update Group','banner-ads') ?></h2>

            <form method="post" action="">

             <table class="form-table">

              <tr valign="top">
               <th scope="row"><?php _e('Embed Code : ','banner-ads') ?></th>
               <td><textarea name="bannerads" rows="2" cols="23"></textarea></td>
              </tr>

              <tr valign="top">
               <th scope="row"><?php _e('Group Name : ','banner-ads') ?></th>
               <td><input type="text" name="group" value=""/></td>
              </tr>

              <tr valign="top">
               <th scope="row"><?php _e('Select Day : ','banner-ads') ?></th>
               <td><select name="showtimes" id="showtimes">
                <option value="Sunday">Sunday</option>
                <option value="Monday">Monday</option>
                <option value="Tuesday">Tuesday</option>
                <option value="Wednesday">Wednesday</option>
                <option value="Thursday">Thursday</option>
                <option value="Friday">Friday</option>
                <option value="Saturday">Saturday</option>
               </select></td>
              </tr>

             </table>

             <p class="submit">
              <input type="submit" class="button-primary" value="<?php _e('Save Change','banner-ads') ?>" />
             </p>

            </form>

            <h2><?php _e('Delete Group','banner-ads') ?></h2>

            <form method="post" action="">

             <table class="form-table">

              <tr valign="top">
               <th scope="row"><?php _e('Select Group : ','banner-ads') ?></th>
               <td><select name="delsgroup" id="delsgroup">
               <?php foreach ($adsgroup as $adgroup) {
                 $showads = $adgroup->adsgrp;
                 if(in_array($showads,$nonduplicated)) {
                   continue;
                 } else {
                   array_push($nonduplicated,$showads);
                   echo '<option value="'.$showads.'">'.$showads.'</option>';
                 }
               }?>
               </select></td>
              </tr>

             </table>

             <p class="submit">
               <input type="submit" class="button-primary" value="<?php _e('Delete Group','banner-ads') ?>" />
             </p>

            </form>
           </div>

           <div class="right_smart">
             <div id="result"></div>
             <h2><?php _e('View Banner','banner-ads') ?></h2>
             <table class="form-table">
              <tr valign="top">
               <th scope="row"><?php _e('Select Group : ','banner-ads') ?></th>
               <td><select name="listadsgroup" id="listadsgroup">
               <?php foreach ($adsgroup as $adgroup) {
                $showads = $adgroup->adsgrp;
                if(in_array($showads,$noduplicated)) {
                 continue;
                } else {
                 array_push($noduplicated,$showads);
                 echo '<option value="'.$showads.'">'.$showads.'</option>';
                }
               }?>
               </select></td>
              </tr>

              <tr valign="top">
               <th scope="row"><?php _e('Select Day : ','banner-ads') ?></th>
               <td><select name="timeshowing" id="timeshowing">
                <option value="Sunday">Sunday</option>
                <option value="Monday">Monday</option>
                <option value="Tuesday">Tuesday</option>
                <option value="Wednesday">Wednesday</option>
                <option value="Thursday">Thursday</option>
                <option value="Friday">Friday</option>
                <option value="Saturday">Saturday</option>
               </select></td>
              </tr>
             </table>
           </div>
          </div>
         <?php
         } else if($current_tab == 'in_post_ad_settings' ) {
           global $wpdb;
           $table_namea = $wpdb->prefix . "bannerad";
           $adgroup = $wpdb->get_results(
            "
              SELECT * FROM $table_namea
            "
           );

           $nonduplicate = array();
           $noduplicate = array();?>

          <div class="main_smart">
           <div class="left_smart">

            <h2><?php _e('Create or Update Group','banner-ads') ?></h2>

            <form method="post" action="">

             <table class="form-table">

              <tr valign="top">
               <th scope="row"><?php _e('Embed Code : ','banner-ads') ?></th>
               <td><textarea name="bannerad" rows="2" cols="23"></textarea></td>
              </tr>

              <tr valign="top">
               <th scope="row"><?php _e('Group Name : ','banner-ads') ?></th>
               <td><input type="text" name="adgroup" value=""/></td>
              </tr>

             </table>

             <p class="submit">
              <input type="submit" class="button-primary" value="<?php _e('Save Change','banner-ads') ?>" />
             </p>

            </form>

            <h2><?php _e('Delete Group','banner-ads') ?></h2>

            <form method="post" action="">

             <table class="form-table">

              <tr valign="top">
               <th scope="row"><?php _e('Select Group : ','banner-ads') ?></th>
               <td><select name="delgroup" id="delgroup">
               <?php foreach ($adgroup as $adgrop) {
                 $showad = $adgrop->adgrp;
                 if(in_array($showad,$nonduplicate)) {
                   continue;
                 } else {
                   array_push($nonduplicate,$showad);
                   echo '<option value="'.$showad.'">'.$showad.'</option>';
                 }
               }?>
               </select></td>
              </tr>

             </table>

             <p class="submit">
              <input type="submit" class="button-primary" value="<?php _e('Delete Group','banner-ads') ?>" />
             </p>
            </form>
           </div>

           <div class="right_smart">
             <div id="results"></div>
             <h2><?php _e('View Banner','banner-ads') ?></h2>
             <table class="form-table">
              <tr valign="top">
               <th scope="row"><?php _e('Select Group : ','banner-ads') ?></th>
               <td><select name="listadgroup" id="listadgroup">
                echo '<option value=""></option>';
               <?php foreach ($adgroup as $adgrpp) {
                $showad = $adgrpp->adgrp;
                if(in_array($showad,$noduplicate)) {
                 continue;
                } else {
                 array_push($noduplicate,$showad);
                 echo '<option value="'.$showad.'">'.$showad.'</option>';
                }
               }?>
               </select></td>
              </tr>
             </table>
           </div>
          </div>
         <?php }
       }
};
?>