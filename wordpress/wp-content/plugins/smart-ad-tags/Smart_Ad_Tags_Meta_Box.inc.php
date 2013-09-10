<?php

add_action( 'load-post.php', 'smart_ad_tags_meta_boxes_setup' );
add_action( 'load-post-new.php', 'smart_ad_tags_meta_boxes_setup' );

function smart_ad_tags_meta_boxes_setup() {
 add_action( 'add_meta_boxes', 'smart_ad_tags_add_meta_boxes' );
 add_action( 'save_post', 'smart_tag_meta_box_save' );
 wp_enqueue_style( 'smartadtagstylesheet', plugins_url( '/css/smartadtagstylesheet.css', __FILE__ ), false, '1.3', 'all' );
}

function smart_ad_tags_add_meta_boxes() {

 add_meta_box(
  'smart-ad-tags-post',
  esc_html__( 'Smart Tag', 'banner-ads' ),
  'smart_ad_tags_meta_box',
  'post',
  'side',
  'default'
 );

 add_meta_box(
  'smart-ad-tags-post',
  esc_html__( 'Smart Tag', 'banner-ads' ),
  'smart_ad_tags_meta_box',
  'page',
  'side',
  'default'
 );

}

function smart_tag_meta_box_save( $post_id ) {
 if( defined( 'DOING_AUTOSAVE' ) && DOING_AUTOSAVE )
  return;
 if( !isset( $_POST['smart_meta_box_nonce'] ) || !wp_verify_nonce( $_POST['smart_meta_box_nonce'], 'smart_tag_meta_box_nonce' ) )
  return;

 if ( 'page' == $_POST['post_type'] ) {
    if ( !current_user_can( 'edit_page', $post_id ) )
        return;
 }
 else {
    if ( !current_user_can( 'edit_post', $post_id ) )
        return;
 }
 if( isset( $_POST['smart_meta_box_top_select'] ) && $_POST['smart_meta_box_top_select']!=='' ) {
  update_post_meta( $post_id, '_smart_meta_box_top_select', esc_attr( $_POST['smart_meta_box_top_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_align_select', esc_attr( $_POST['smart_meta_box_top_align_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_word_select', esc_attr( $_POST['smart_meta_box_top_word_select'] ) );
 } else {
  delete_post_meta($post_id, '_smart_meta_box_top_select');
  delete_post_meta($post_id, '_smart_meta_box_top_align_select');
  delete_post_meta($post_id, '_smart_meta_box_top_word_select');
 }
 if( isset( $_POST['smart_meta_box_top_two_select'] ) && $_POST['smart_meta_box_top_two_select']!=='' ) {
  update_post_meta( $post_id, '_smart_meta_box_top_two_select', esc_attr( $_POST['smart_meta_box_top_two_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_two_align_select', esc_attr( $_POST['smart_meta_box_top_two_align_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_two_word_select', esc_attr( $_POST['smart_meta_box_top_two_word_select'] ) );
 } else {
  delete_post_meta($post_id, '_smart_meta_box_top_two_select');
  delete_post_meta($post_id, '_smart_meta_box_top_two_align_select');
  delete_post_meta($post_id, '_smart_meta_box_top_two_word_select');
 }
 if( isset( $_POST['smart_meta_box_top_three_select'] ) && $_POST['smart_meta_box_top_three_select']!=='') {
  update_post_meta( $post_id, '_smart_meta_box_top_three_select', esc_attr( $_POST['smart_meta_box_top_three_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_three_align_select', esc_attr( $_POST['smart_meta_box_top_three_align_select'] ) );
  update_post_meta( $post_id, '_smart_meta_box_top_three_word_select', esc_attr( $_POST['smart_meta_box_top_three_word_select'] ) );
 } else {
    delete_post_meta($post_id, '_smart_meta_box_top_three_select');
    delete_post_meta($post_id, '_smart_meta_box_top_three_align_select');
    delete_post_meta($post_id, '_smart_meta_box_top_three_word_select');
 }
 if( isset( $_POST['smart_meta_box_bottom_select'] ) && $_POST['smart_meta_box_bottom_select']!=='' ) {
  update_post_meta( $post_id, '_smart_meta_box_bottom_select', esc_attr( $_POST['smart_meta_box_bottom_select'] ) );
 } else {
    delete_post_meta($post_id, '_smart_meta_box_bottom_select');
 }
 if( isset( $_POST['smart_meta_box_side_select'] ) && $_POST['smart_meta_box_side_select']!=='')  {
  update_post_meta( $post_id, '_smart_meta_box_side_select', esc_attr( $_POST['smart_meta_box_side_select'] ) );
 } else {
    delete_post_meta($post_id, '_smart_meta_box_side_select');
 }
}

function smart_ad_tags_meta_box( $object, $box ) {
 global $wpdb;
 $table_namea = $wpdb->prefix . "bannerad";
 $yourad = $wpdb->get_results(
      "
        SELECT *
        FROM $table_namea
      "
 );

 $metavalues = get_post_custom( $post->ID );
 $topselected = isset( $metavalues['_smart_meta_box_top_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_select'][0] ) : '';
 $top_align_selected = isset( $metavalues['_smart_meta_box_top_align_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_align_select'][0] ) : '';
 $top_word_selected = isset( $metavalues['_smart_meta_box_top_word_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_word_select'][0] ) : '';

 $top_two_selected = isset( $metavalues['_smart_meta_box_top_two_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_two_select'][0] ) : '';
 $top_align_two_selected = isset( $metavalues['_smart_meta_box_top_two_align_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_two_align_select'][0] ) : '';
 $top_two_word_selected = isset( $metavalues['_smart_meta_box_top_two_word_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_two_word_select'][0] ) : '';

 $top_three_selected = isset( $metavalues['_smart_meta_box_top_three_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_three_select'][0] ) : '';
 $top_align_three_selected = isset( $metavalues['_smart_meta_box_top_three_align_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_three_align_select'][0] ) : '';
 $top_three_word_selected = isset( $metavalues['_smart_meta_box_top_three_word_select'] ) ? esc_attr( $metavalues['_smart_meta_box_top_three_word_select'][0] ) : '';

 $bottomselected = isset( $metavalues['_smart_meta_box_bottom_select'] ) ? esc_attr( $metavalues['_smart_meta_box_bottom_select'][0] ) : '';
 $sideselected = isset( $metavalues['_smart_meta_box_side_select'] ) ? esc_attr( $metavalues['_smart_meta_box_side_select'][0] ) : '';
 wp_nonce_field( 'smart_tag_meta_box_nonce', 'smart_meta_box_nonce' );  ?>
<div class="smart-ad-tags-meta-box">
  <p>
   <label for="smart-ad-tags-top-content"><?php _e( "Content", 'banner-ads' ); ?></label>
   <br />
   <p>
        <select name="smart_meta_box_top_select" id="smart_meta_box_top_select">
            <option value=""></option>
            <?php
            foreach ($yourad as $ads) {
              $group = $ads->adgrp; ?>
              <option value="<?php echo $group ?>" <?php selected( $topselected, $group ); ?>><?php echo $group ?></option>
            <?php }
            ?>
        </select>

        <select name="smart_meta_box_top_align_select" id="smart_meta_box_top_align_select">
            <option value="Left" <?php selected( $top_align_selected, "Left" ); ?>>Left</option>
            <option value="Right" <?php selected( $top_align_selected, "Right" ); ?>>Right</option>
        </select>

        <select name="smart_meta_box_top_word_select" id="smart_meta_box_top_word_select">
            <option value="0" <?php selected( $top_word_selected, "0" ); ?>>0</option>
            <option value="150" <?php selected( $top_word_selected, "150" ); ?>>150</option>
            <option value="300" <?php selected( $top_word_selected, "300" ); ?>>300</option>
        </select>
    </p>
    <p>

        <select name="smart_meta_box_top_two_select" id="smart_meta_box_top_two_select">
            <option value=""></option>
            <?php
            foreach ($yourad as $ads) {
              $group = $ads->adgrp; ?>
              <option value="<?php echo $group ?>" <?php selected( $top_two_selected, $group ); ?>><?php echo $group ?></option>
            <?php }
            ?>
        </select>

        <select name="smart_meta_box_top_two_align_select" id="smart_meta_box_top_two_align_select">
            <option value="Left" <?php selected( $top_align_two_selected, "Left" ); ?>>Left</option>
            <option value="Right" <?php selected( $top_align_two_selected, "Right" ); ?>>Right</option>
        </select>

        <select name="smart_meta_box_top_two_word_select" id="smart_meta_box_top_two_word_select">
            <option value="0" <?php selected( $top_two_word_selected, "0" ); ?>>0</option>
            <option value="150" <?php selected( $top_two_word_selected, "150" ); ?>>150</option>
            <option value="300" <?php selected( $top_two_word_selected, "300" ); ?>>300</option>
        </select>
    </p>
    <p>

        <select name="smart_meta_box_top_three_select" id="smart_meta_box_top_three_select">
            <option value=""></option>
            <?php
            foreach ($yourad as $ads) {
              $group = $ads->adgrp; ?>
              <option value="<?php echo $group ?>" <?php selected( $top_three_selected, $group ); ?>><?php echo $group ?></option>
            <?php }
            ?>
        </select>

        <select name="smart_meta_box_top_three_align_select" id="smart_meta_box_top_three_align_select">
            <option value="Left" <?php selected( $top_align_three_selected, "Left" ); ?>>Left</option>
            <option value="Right" <?php selected( $top_align_three_selected, "Right" ); ?>>Right</option>
        </select>

        <select name="smart_meta_box_top_three_word_select" id="smart_meta_box_top_three_word_select">
            <option value="0" <?php selected( $top_three_word_selected, "0" ); ?>>0</option>
            <option value="150" <?php selected( $top_three_word_selected, "150" ); ?>>150</option>
            <option value="300" <?php selected( $top_three_word_selected, "300" ); ?>>300</option>
        </select>
    </p>
  </p>
  <p>
   <label for="smart-ad-tags-bottom-content"><?php _e( "Bottom", 'banner-ads' ); ?></label>
   <br />
   <p>
        <select name="smart_meta_box_bottom_select" id="smart_meta_box_bottom_select">
            <option value=""></option>
            <?php
            foreach ($yourad as $ads) {
              $group = $ads->adgrp; ?>
              <option value="<?php echo $group ?>" <?php selected( $bottomselected, $group ); ?>><?php echo $group ?></option>
            <?php }
            ?>
        </select>
    </p>
  </p>
  <p>
   <label for="smart-ad-tags-side"><?php _e( "Side Bar", 'banner-ads' ); ?></label>
   <br />
   <p>
        <select name="smart_meta_box_side_select" id="smart_meta_box_side_select">
            <option value=""></option>
            <?php
            foreach ($yourad as $ads) {
              $group = $ads->adgrp; ?>
              <option value="<?php echo $group ?>" <?php selected( $sideselected, $group ); ?>><?php echo $group ?></option>
            <?php }
            ?>
        </select>
    </p>
  </p>
</div>
<?php }
?>