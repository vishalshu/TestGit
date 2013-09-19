import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vishalshu
 *
 */
public class ContactIndex {
	private String contactId;
	private String subscriptionId;
	private String primaryEmail;
	private String givenName;
	private String familyName;
	private String honorificPrefix;
	private String honorificSuffix;
	private String nickname;
	private String organization;
	private Set<Category> categoryNames = new HashSet<Category>();
	private String providerSymbolicName;
	private String url;
	private Date birthday;
	private String favorite;
	private String profileId;
	private String recent;
	private Set<String> collectionNames = new HashSet<String>();
	private String thumbnailSmall;
	private String thumbnailMedium;
	private String thumbnailLarge;
	private Date lastAccessed;
	private Date lastIndexed;
	private String id;
	
	public void setId(String id){
		this.id = id;
	}
	public String id(){
		return id;
	}
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}
	public Set<String> getCollectionNames() {
		return collectionNames;
	}
	public boolean addCollectionName(String collectionName){
		return collectionNames.add(collectionName);
	}
	
	public void setCollectionNames(Set<String> collectionNames) {
		this.collectionNames = collectionNames;
	}
	public String getThumbnailSmall() {
		return thumbnailSmall;
	}
	public void setThumbnailSmall(String thumbnailSmall) {
		this.thumbnailSmall = thumbnailSmall;
	}
	public String getThumbnailMedium() {
		return thumbnailMedium;
	}
	public void setThumbnailMedium(String thumbnailMedium) {
		this.thumbnailMedium = thumbnailMedium;
	}
	public String getThumbnailLarge() {
		return thumbnailLarge;
	}
	public void setThumbnailLarge(String thumbnailLarge) {
		this.thumbnailLarge = thumbnailLarge;
	}
	public Set<Category> getCategoryNames() {
		return categoryNames;
	}
	public boolean addCategoryName(Category categoryName){
		return categoryNames.add(categoryName);
	}
	public void setCategoryNames(Set<Category> categoryNames) {
		this.categoryNames = categoryNames;
	}
	public String getProviderSymbolicName() {
		return providerSymbolicName;
	}
	public void setProviderSymbolicName(String providerSymbolicName) {
		this.providerSymbolicName = providerSymbolicName;
	}
	public Date getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	public Date getLastIndexed() {
		return lastIndexed;
	}
	public void setLastIndexed(Date lastIndexed) {
		this.lastIndexed = lastIndexed;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHonorificPrefix() {
		return honorificPrefix;
	}
	public void setHonorificPrefix(String honorificPrefix) {
		this.honorificPrefix = honorificPrefix;
	}
	public String getHonorificSuffix() {
		return honorificSuffix;
	}
	public void setHonorificSuffix(String honorificSuffix) {
		this.honorificSuffix = honorificSuffix;
	}
	
	
	

}
