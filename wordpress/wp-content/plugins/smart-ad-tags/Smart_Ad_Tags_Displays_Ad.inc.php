<?php

//Register smart ad tags sidebar widget

wp_register_sidebar_widget(
    'smart_ad_tags_widget',
    'Smart Ad',
    'smart_ad_tags_widget',
    array(
        'description' => 'Smart Widget'
    )
);

function smart_ad_tags_widget() {

   if( is_single() || is_page() ) {

    global $wpdb;
    $table_namea = $wpdb->prefix . "bannerad";
    $side_ad_value = get_post_meta(get_the_ID(), '_smart_meta_box_side_select', true);
    $yourad = $wpdb->get_row("SELECT * FROM $table_namea WHERE adgrp = '$side_ad_value'", ARRAY_A);
    echo "<li id='smart-tag-side-widget' class='widget widget_smart_side'><div class='smart-ad-side-tag'>";
    echo stripslashes($yourad['bannarad']);
    echo "</div></li>";
   }

}

function displays_smart_tag_in_post_content($content) {
   $bottom = '';
   if ( is_single() || is_page() ) {
    $topfind = false;
    $secondtopfind = false;
    $thirdtopfind = false;
    $ad_id = get_the_ID();
    $top_ad = get_post_meta($ad_id,'_smart_meta_box_top_select', true);
    $top_ad_align = get_post_meta($ad_id,'_smart_meta_box_top_align_select', true);
    $top_ad_word = get_post_meta($ad_id,'_smart_meta_box_top_word_select', true);
    $top_two_ad = get_post_meta($ad_id,'_smart_meta_box_top_two_select', true);
    $top_two_ad_align = get_post_meta($ad_id,'_smart_meta_box_top_two_align_select', true);
    $top_two_ad_word =  get_post_meta($ad_id,'_smart_meta_box_top_two_word_select', true);
    $top_three_ad = get_post_meta($ad_id,'_smart_meta_box_top_three_select', true);
    $top_three_ad_align = get_post_meta($ad_id,'_smart_meta_box_top_three_align_select', true);
    $top_three_ad_word =  get_post_meta($ad_id,'_smart_meta_box_top_three_word_select', true);
    $bottom_ad = get_post_meta($ad_id,'_smart_meta_box_bottom_select', true);
    global $wpdb;
    $table_namea = $wpdb->prefix . "bannerad";
    $yourad = $wpdb->get_results (
       "
        SELECT *
        FROM $table_namea
       "
    );

    foreach ($yourad as $ads) {
     $group = $ads->adgrp;
     if($group == $top_ad) {
      $topad = $ads->bannarad;
      $topad = stripslashes($topad);
      if($topad !== '')
        $topad = "<div class='smart-ad-top-tag' style='float:$top_ad_align;'>$topad</div>";
     }
     if($group == $top_two_ad) {
      $toptwoad = $ads->bannarad;
      $toptwoad = stripslashes($toptwoad);
      if($toptwoad !== '')
        $toptwoad = "<div class='smart-ad-top-tag' style='float:$top_two_ad_align;'>$toptwoad</div>";
     }
     if($group == $top_three_ad){
      $topthreead = $ads->bannarad;
      $topthreead = stripslashes($topthreead);
      if($topthreead !== '')
        $topthreead = "<div class='smart-ad-top-tag' style='float:$top_three_ad_align;'>$topthreead</div>";
     }
    }

    if($topad !== '' || $toptwoad !== '' || $topthreead !== '') {
     $paragraph = explode("</p>", $content);
     foreach($paragraph as &$element) {
       $elemen = substr($element, (strpos($element, "<p>") + 3));
       $elements = explode(" ", $elemen);
       $totalWords = count( $elements );
       if(!$topfind && $topad && $totalWords >= $top_ad_word) {
            $element = $topad . $element;
            $topfind = true;
            continue;
       }
       else if(!$secondtopfind && $toptwoad && $totalWords >= $top_two_ad_word) {
            $element = $toptwoad . $element;
            $secondtopfind = true;
            continue;
       }
       else if(!$thirdtopfind && $topthreead && $totalWords >= $top_three_ad_word) {
            $element = $topthreead . $element;
            $thirdtopfind = true;
            continue;
       }
     }

     if($topfind || $secondtopfind || $thirdtopfind) {
       $content = implode("</p>", $paragraph);
     }
    }

    foreach ($yourad as $ads) {
     $group = $ads->adgrp;
     if($group == $bottom_ad) {
      $ad = $ads->bannarad;
      $ad = stripslashes($ad);
      if($ad !== '') {
        $bottom = "<div class='smart-ad-bottom-tag'>$ad</div>";
      }
      break;
     }
    }
  }

  return $content.$bottom;
}

add_filter('the_content','displays_smart_tag_in_post_content');
?>