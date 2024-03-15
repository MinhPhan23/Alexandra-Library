package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alexandria_library.R;
import com.alexandria_library.data.DataInterface.IBookPersistent;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.IBookListFilter;
import com.alexandria_library.logic.IBookListRanker;
import com.alexandria_library.logic.ISearchService;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.logic.Exception.SearchServiceException;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.Adapter.AllBookListAdapter;
import com.alexandria_library.presentation.Adapter.AllGenresListAdapter;
import com.alexandria_library.presentation.Adapter.AllTagsListAdapter;
import com.alexandria_library.presentation.Adapter.FilterBookAdapter;
import com.alexandria_library.presentation.Adapter.FinishedBookAdapter;
import com.alexandria_library.presentation.Adapter.InProgressBookAdapter;
import com.alexandria_library.presentation.Adapter.LibraryBookListAdapter;
import com.alexandria_library.presentation.Adapter.SearchListAdapter;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Booklist searchList;
    private boolean grid = true;
    private AllBookListAdapter allBookAdapter;
    private FinishedBookAdapter finishedBookAdapter;
    private InProgressBookAdapter inProgressBookAdapter;
    private LibraryBookListAdapter libraryBookListAdapter;
    private SearchListAdapter searchListAdapter;
    private AllTagsListAdapter tagsAdapter;
    private AllGenresListAdapter genresAdapter;
    private FilterBookAdapter filterBookAdapter;
    private SideBarService sideBarService;
    private ISearchService searchService;
    private IBookListFilter bookListFilter;
    private IBookPersistent bookPersistent;
    private Button libraryBtn, allListBtn, finishedBtn, inProgressBtn, filterOpenBtn;
    private Button logOut, categoryBtn, account;
    private Button filter;
    private Button searchIcon;
    private FrameLayout expandable;
    private EditText searchInput;
    private RecyclerView recyclerView, filterBox;
    private View rootView, filterPage;
    private boolean library, all, inProgress,finish, filterOpen;
    private Booklist allLibraryBooks;
    private Booklist filterBooks;
    private ArrayList<String> tagsClicked;
    private ArrayList<String> genresClicked;

    private void initializer(){
        bookPersistent = Service.getBookPersistent();
        allLibraryBooks = bookPersistent.getBookList();
        filterBooks = new Booklist();
        library = true; all = false; inProgress = false; finish = false; filterOpen = false;
        searchList = new Booklist();
        searchService = new SearchService();

        sideBarService = LoginActivity.getSideBarService();
        bookListFilter = new BookListFilter();
        tagsClicked = new ArrayList<>();
        genresClicked = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        findByID();

        bookDistributor();
        tagsDisplay();
        genresDisplay();


        /*****
         * get root view
         */
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //check where is the touch position
                    if(!isViewInBounds(recyclerView, (int)event.getRawX(), (int)event.getRawY())){
                        toggleSearchResultGone();
                    }
                }
                return false;
            }
        });

        /*****
         * allListBtn on click
         */
        allListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = true; inProgress = false; finish = false;
                bookDistributor();
                toggleSearchResultGone();
                toggleFilterGone();
            }
        });

        /*****
         * inProgressBtn on click
         */
        inProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = false; inProgress = true; finish = false;
                bookDistributor();
                toggleSearchResultGone();
                toggleFilterGone();
            }
        });

        /*****
         * finishedBtn on click
         */
        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = false; all = false; inProgress = false; finish = true;
                bookDistributor();
                toggleSearchResultGone();
                toggleFilterGone();
            }
        });

        /*****
         * libraryBtn on click
         */
        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library = true; all = false; inProgress = false; finish = false;
                bookDistributor();
                toggleSearchResultGone();
                toggleFilterGone();
            }
        });

        /*****
         * main page change book category's button
         */
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid = !grid;
                bookDistributor();
                toggleSearchResultGone();
                toggleFilterGone();
            }
        });

        /*****
         * main page account button
         */
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                toggleSearchResultGone();
            }
        });

        /*****
         * toggle Button
         */
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition((ViewGroup) expandable.getParent());
                if(expandable.getVisibility() == View.GONE){
                    //from gone to visibility
                    expandable.setVisibility(View.VISIBLE);
                }
                else{
                    //from visibility to gone
                    expandable.setVisibility(View.GONE);
                }
                toggleSearchResultGone();
            }
        });

        /****
         * Get search input
         */
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //don't need to implement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //don't need to implement
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    //get search bar input when search bar changed
                    String input = s.toString();
                    searchList = searchService.searchInput(input);
                    if(searchList.size() == 0){
                        toggleSearchResultGone();
                    }
                    else{
                        toggleSearchResultVisible();
                        SearchBar();
                    }

                }catch(SearchServiceException searchException){
                    searchException.printStackTrace();
                }
            }
        });

        /*****
         * get search bar on focus
         */
        searchInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && searchList.size() > 0){
                    toggleSearchResultVisible();
                }
                else{
                    toggleSearchResultGone();
                }
                toggleFilterGone();
            }
        });

        /*****
         * search icon display result
         */
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSearchResultVisible();
                toggleFilterGone();
            }
        });

        /*****
         * filter button clicked
         */
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterBookDisplay(tagsClicked, genresClicked);
                tagsClicked.clear();
                genresClicked.clear();

            }
        });

        /*****
         * filter page open or close
         */
        filterOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterOpen) {
                    toggleFilterGone();
                }
                else{
                    toggleFilterVisible();
                }
            }
        });
    }


    /*****
     * book distributor is work for distribute which book list showing
     */
    private void bookDistributor(){
        if(all){
            AllBookCategory();
        }
        else if(inProgress){
            InProgressBookCategory();
        }
        else if(finish){
            FinishedBookCategory();
        }
        else if(library){
            LibraryBookCategory();
        }
    }

    private void findByID(){
        //Getting root view ID
        rootView = findViewById(android.R.id.content);

        //Getting library button
        libraryBtn = findViewById(R.id.library_btn);

        //Getting All button
        allListBtn = findViewById(R.id.all_btn);

        //Getting Finished button
        finishedBtn = findViewById(R.id.finished);

        //Getting in progress button
        inProgressBtn = findViewById(R.id.in_progress_btn);

        //Getting log out button
        logOut = findViewById(R.id.log_out_btn);

        //Change Book display Category button
        categoryBtn = findViewById(R.id.book_display_category_button);

        //go to Authentication page
        account = findViewById(R.id.account);
        
        expandable = findViewById(R.id.frameLayout);

        //Getting Search Bar input immediately
        searchInput = findViewById(R.id.searchInput);

        //Getting Search bar Output recycler view
        recyclerView = findViewById(R.id.search_bar_recycle);

        //Getting search result
        searchIcon = findViewById(R.id.search_icon);

        //Getting filter result
        filter = findViewById(R.id.filter_button);

        //Getting filter open btn
        filterOpenBtn = findViewById(R.id.filter_open_btn);

        //Getting filter display box
        filterBox = findViewById(R.id.filter_book);

        //Getting filter control bar
        filterPage = findViewById(R.id.filter_page);
    }

    private void SearchBar(){
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);

        searchListAdapter = new SearchListAdapter(searchList, this, searchService);
        recyclerView.setAdapter(searchListAdapter);

        searchListAdapter.setRecyclerItemClickListener(new SearchListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
            }
        });
    }

    public void toggleSearchResultGone(){
        recyclerView.setVisibility(View.GONE);
    }
    public void toggleSearchResultVisible(){
        recyclerView.setVisibility(View.VISIBLE);
    }
    public void toggleFilterVisible(){
        filterPage.setVisibility(View.VISIBLE);
        filterBox.setVisibility(View.VISIBLE);
        filterOpen = true;
    }
    public void toggleFilterGone(){
        filterPage.setVisibility(View.GONE);
        filterBox.setVisibility(View.GONE);
        filterOpen = false;
        bookDistributor();
    }

    public boolean isViewInBounds(View view, int x, int y){
        int [] location = new int [2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        return(x>viewX && x < (viewWidth+viewX)) && (y>viewY && y<(viewHeight+viewY));
    }


    private void LibraryBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            libraryBookListAdapter = new LibraryBookListAdapter(this);
            recyclerView.setAdapter(libraryBookListAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            libraryBookListAdapter = new LibraryBookListAdapter(this);
            recyclerView.setAdapter(libraryBookListAdapter);
        }

        libraryBookListAdapter.setRecyclerItemClickListener(new LibraryBookListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                toggleSearchResultGone();
            }
        });
    }
    private void AllBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            allBookAdapter = new AllBookListAdapter(this);
            recyclerView.setAdapter(allBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            allBookAdapter = new AllBookListAdapter(this);
            recyclerView.setAdapter(allBookAdapter);
        }

        allBookAdapter.setRecyclerItemClickListener(new AllBookListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                toggleSearchResultGone();
            }
        });
    }
    
    private void FinishedBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            finishedBookAdapter = new FinishedBookAdapter(this);
            recyclerView.setAdapter(finishedBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            finishedBookAdapter = new FinishedBookAdapter(this);
            recyclerView.setAdapter(finishedBookAdapter);
        }

        finishedBookAdapter.setRecyclerItemClickListener(new FinishedBookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                toggleSearchResultGone();
            }
        });
    }
    
    private void InProgressBookCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            inProgressBookAdapter = new InProgressBookAdapter(this);
            recyclerView.setAdapter(inProgressBookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            inProgressBookAdapter = new InProgressBookAdapter(this);
            recyclerView.setAdapter(inProgressBookAdapter);
        }

        inProgressBookAdapter.setRecyclerItemClickListener(new InProgressBookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                toggleSearchResultGone();
            }
        });
    }

    private void tagsDisplay(){
        RecyclerView recyclerView = findViewById(R.id.tags_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        tagsAdapter = new AllTagsListAdapter(this, bookListFilter);
        recyclerView.setAdapter(tagsAdapter);

        tagsAdapter.setRecyclerItemClickListener(new AllTagsListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                String getTagName = tagsAdapter.getTagsName(position);
                if(getTagName != null){
                    tagsClicked.add(getTagName);
                }
            }
        });
    }

    private void genresDisplay(){
        RecyclerView recyclerView = findViewById(R.id.genres_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        genresAdapter = new AllGenresListAdapter(this, bookListFilter);
        recyclerView.setAdapter(genresAdapter);

        genresAdapter.setRecyclerItemClickListener(new AllGenresListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                String getGenreName = genresAdapter.getGenreName(position);
                if(getGenreName != null){
                    genresClicked.add(getGenreName);
                }
            }
        });
    }

    private void filterBookDisplay(ArrayList<String> tags, ArrayList<String> genre){
        RecyclerView recyclerView = findViewById(R.id.filter_book);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        filterBookAdapter = new FilterBookAdapter(this, bookListFilter, allLibraryBooks, tags, genre);
        recyclerView.setAdapter(filterBookAdapter);
        filterBookAdapter.setRecyclerItemClickListener(new FilterBookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
            }
        });

    }
}