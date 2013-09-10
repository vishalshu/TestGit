<?php
 if (!defined('ABSPATH') && !defined('WP_UNINSTALL_PLUGIN')){
   exit();
 }

 global $wpdb;
 //Drop advertisement tables
 $table_namea = $wpdb->prefix . "bannerad";
 $table_nameb = $wpdb->prefix . "bannerads";
 $sqla = "DROP TABLE " . $table_namea . ";";
 $sqlb = "DROP TABLE " . $table_nameb . ";";
 $wpdb->query($sqla);
 $wpdb->query($sqlb);
 require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
 dbDelta($sqla);
 dbDelta($sqlb);

 //credit to wordpress codex for providing below solution

 $allposts = get_posts('numberposts=-1&post_type=post&post_status=any');

 foreach( $allposts as $postinfo) {
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_word_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_word_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_word_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_bottom_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_side_select');
 }

 $allpages = get_pages();

 foreach( $allpages as $pageinfo) {
    delete_post_meta($pageinfo->ID, '_smart_meta_box_top_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_word_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_two_word_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_align_select');
    delete_post_meta($postinfo->ID, '_smart_meta_box_top_three_word_select');
    delete_post_meta($pageinfo->ID, '_smart_meta_box_bottom_select');
    delete_post_meta($pageinfo->ID, '_smart_meta_box_side_select');
 }
?>