<?php
/*
Plugin Name: Smart AD Tags
Plugin URI: http://wordpress.org/extend/plugins/smart-ad-tags/
Description: Smart AD Tags is an easy to use all in one advertisement package plugin which helps bloggers to insert advertisement in various locations of a post.
Version: 2.7
Author: Gadgets-Code.Com
Author URI: http://onmouseenter.com/
License: GPLv2
*/

/* Copyright 2013 Gadgets-Code.Com (e-mail : morning131@hotmail.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program; if not, please visit <http://www.gnu.org/licenses/>.

*/

include_once('Smart_Ad_Tags_Settings_Class.inc.php');
include_once('Smart_Ad_Tags_Meta_Box.inc.php');
include_once('Smart_Ad_Tags_Displays_Ad.inc.php');

add_filter('plugin_action_links', 'smart_plugin_settings_link', 10, 2);
 function smart_plugin_settings_link($links, $file) {

    if ( $file == 'smart-ad-tags/smart-ad-tags.php' ) {
        /* Insert the setting and tutorial link */
        $links['settings'] = sprintf( '<a href="%s"> %s </a>', admin_url( 'admin.php?page=banner_ads' ), __( 'Settings', 'smart-ads-tag' ) );
        $links['tutor'] = sprintf( '<a href="%s"> %s </a>', 'http://onmouseenter.com/how-to-insert-advertisement-into-publisher-wordpress-blog/', __( 'Tutorial', 'smart-ads-tag' ) );
    }
    return $links;

 }

function bannerads_install() {

   global $wpdb;
   $table_namea = $wpdb->prefix . "bannerad";
   $table_nameb = $wpdb->prefix . "bannerads";

   if($wpdb->get_var("show tables like '$table_nameb'") != $table_nameb) {

      $sql = "CREATE TABLE " . $table_nameb . " (
      id mediumint(9) NOT NULL AUTO_INCREMENT,
      time bigint(11) DEFAULT '0' NOT NULL,
      showtimes text NOT NULL,
      adsgrp text NOT NULL,
      bannerad text NOT NULL,
      UNIQUE KEY id (id)
      );";

      require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
      dbDelta($sql);

   }

   if($wpdb->get_var("show tables like '$table_namea'") != $table_namea) {

      $sql = "CREATE TABLE " . $table_namea . " (
      id mediumint(9) NOT NULL AUTO_INCREMENT,
      time bigint(11) DEFAULT '0' NOT NULL,
      adgrp text NOT NULL,
      bannarad text NOT NULL,
      UNIQUE KEY id (id)
      );";

      require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
      dbDelta($sql);

   }
}

add_shortcode("banners","banner");

function banner($atts) {

  extract(shortcode_atts(array("groups"=>''),$atts));
  global $wpdb;
  $table_nameb = $wpdb->prefix . "bannerads";
  $now =  getDate();
  $timenow = $now["weekday"];

  $yourad = $wpdb->get_results(
        "
        SELECT *
        FROM $table_nameb
    "
   );

   foreach ($yourad as $ads) {

        $showt = $ads->showtimes;

        if($showt == $timenow) {
          $ad = $ads->bannerad;
          $ad = stripslashes($ad);
          break;
        }
    }

  return $ad;
}

register_activation_hook(__FILE__,'bannerads_install');
add_filter( 'comment_text', 'do_shortcode' );
add_filter('widget_text', 'shortcode_unautop');
add_filter('widget_text', 'do_shortcode');
wp_register_style('smartAdTagsStyleSheets', plugins_url( '/css/smart-style.css' , __FILE__ ), array(), '79', 'all' );
wp_enqueue_style( 'smartAdTagsStyleSheets');

add_action( 'plugins_loaded', create_function( '', '$smart_ad_tags_plugin = new Smart_Ad_Tags_Settings;' ) );
?>